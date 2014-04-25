(ns web.hello
  (:require [clojure.browser.repl]
            [goog.dom]))

(def *paper*)

(defn ^:export circles []
  (do
    (.clear *paper*)
    (dotimes [cnt 10]
      (let [xoffset (+ 100 (* (* 10 (rand-int 10)) cnt))
            yoffset (+ 100 (* (* 10 (rand-int 10)) cnt))
            size (+ 30 (rand-int 100))
            c (.circle *paper* xoffset yoffset 50)]
        (.animate c (clj->js {:fill "#22EEFF" :stroke "#000" :stroke-width 80 :stroke-opacity 0.5}) 3000)))))

(defn start []
  (binding [*paper* (js/Raphael 0 0 "100%" "100%")]
    (circles)))

(set! (.-onload js/window) start)

(js/alert "hi")

*paper*
;(goog.dom/getElement "display")

(def p (js/Raphael 0 0 "100%" "100%"))

(.clear p)
(.circle p 300 300)
