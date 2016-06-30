(set-env!
	:resource-paths #{"resources"}
	:dependencies '[[cljsjs/boot-cljsjs "0.5.2"  :scope "test"]
									[cljsjs/react "0.14.3-0"]
									[cljsjs/react-dom "0.14.3-1"]
									[cljsjs/classnames "2.2.3-0"]
									[cljsjs/react-input-autosize "0.6.10-0"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "1.0.0-beta14")
(def +version+ (str +lib-version+ "-0"))

(task-options!
	pom  {:project     'no.animalia/react-select
				:version     +version+
				:description "A flexible and beautiful Select Input control for ReactJS with multiselect, autocomplete and ajax support."
				:url         "http://animalia.github.io/react-select/"
				:scm         {:url "https://github.com/animalia/react-select"}
				:license     {"MIT" "http://opensource.org/licenses/MIT"}}

	push {:file (str "target/react-select-" +version+  ".jar")
				:repo-map {:url "http://62.89.42.8:8082/nexus/content/repositories/releases/"
									 :username (System/getenv "nexus_user")
									 :password (System/getenv "nexus_password")}})

(require '[boot.core :as c]
				 '[boot.tmpdir :as tmpd]
				 '[clojure.java.io :as io]
				 '[clojure.string :as string])

(deftask package []
				 (comp
					 (download :url (str "https://github.com/animalia/react-select/archive/v" +lib-version+ ".zip")
										 ;:checksum "566959B5D9D1B240D14C356721A605CF"
										 :unzip true)

					 (sift :move {#"^react-select.*[/ \\]dist[/ \\]react-select.js$" "cljsjs/react-select/development/react-select.inc.js"
												#"^react-select.*[/ \\]dist[/ \\]react-select.min\.js$" "cljsjs/react-select/production/react-select.min.inc.js"
												#"^react-select.*[/ \\]dist[/ \\]react-select.css$" "cljsjs/react-select/common/react-select.inc.css"})

					 (sift :include #{#"^cljsjs"})

					 (deps-cljs :name "cljsjs.react-select"
											:requires ["cljsjs.react"
																 "cljsjs.react.dom"
																 "cljsjs.classnames"
																 "cljsjs.react-input-autosize"])
					 (pom)
					 (jar)))
