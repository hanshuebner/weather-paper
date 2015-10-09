(ns weather-paper.openweather
  (:require [clj-http.client :as http]))

(defn get-weather [location]
  (http/get "http://api.openweathermap.org/data/2.5/weather"
            {:as :json
             :coerce :always
             :throw-exceptions true
             :query-params {:units "metric"
                            :q location}}))
