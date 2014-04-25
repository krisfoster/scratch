(ns web.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure.core :refer (GET defroutes)]
            ring.adapter.jetty
            [hiccup
              [page :refer [html5]]
              [element :refer [javascript-tag]]
              [page :refer [include-js include-css]]]
            [cemerick.austin.repls :refer (browser-connected-repl-js)]))

(defn run-clojurescript [path init]
  (list
    (include-js "/js/raphael-min.js")
    (include-js path)
    ;(javascript-tag init)
))


(defn index-page []
  (html5
    [:head
      [:title "This is a tes"]
      (include-css "/css/style.css")
       (run-clojurescript
          "/app.js"
          "web.hello.circles()")]
    [:body
      [:div {:id "display" :class "gradient"} ]
      (javascript-tag "web.hello.circles()")
      [:script (browser-connected-repl-js)]]))

(defroutes app-routes
  (GET "/" [] (index-page))
  (route/resources "/")
  (route/not-found "Not Found"))

(defn run
  []
  (defonce ^:private server
    (ring.adapter.jetty/run-jetty #'app-routes {:port 8081 :join? false}))
  server)

;;;
;;; To get the brepl going....
;;;
(run)
;(def repl-env (reset! cemerick.austin.repls/browser-repl-env
;                   (cemerick.austin/repl-env)))
;(cemerick.austin.repls/cljs-repl repl-env)
