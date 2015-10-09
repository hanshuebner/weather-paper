(ns weather-paper.routes.home
  (:require [compojure.core :refer :all]
            [weather-paper.views.layout :as layout]))

(defn home []
  (layout/common [:h1 "Hallo Welt!"]))

(defroutes home-routes
  (GET "/" [] (home)))
