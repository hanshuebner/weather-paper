(ns weather-paper.world-weather-online
  (:require [clj-http.client :as http]))

(def api-key "ac658121f2b3fa57a12c400d6eb47")

(def base-url "http://api.worldweatheronline.com/free/v2/weather.ashx")

(defn get-weather [location]
  (-> (http/get base-url
                {:throw-exceptions true
                 :as :json
                 :coerce :always
                 :query-params {:key api-key
                                :num_of_days 5
                                :format "json"
                                :q location}})
      :body
      :data))

