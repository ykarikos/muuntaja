(defproject metosin/muuntaja "0.4.1"
  :description "Clojure library for fast http format negotiation, encoding and decoding."
  :url "https://github.com/metosin/muuntaja"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments "same as Clojure"}
  :source-paths ["src/clj"]
  :javac-options ["-Xlint:unchecked" "-target" "1.7" "-source" "1.7"]
  :java-source-paths ["src/java"]
  :dependencies [[cheshire "5.8.0"]
                 [com.cognitect/transit-clj "0.8.300"]]
  :plugins [[lein-codox "0.10.3"]]
  :codox {:src-uri "http://github.com/metosin/muuntaja/blob/master/{filepath}#L{line}"
          :output-path "doc"
          :metadata {:doc/format :markdown}}
  :deploy-repositories [["releases" :clojars]]
  :profiles {:dev {:jvm-opts ^:replace ["-server"]
                   :dependencies [[org.clojure/clojure "1.8.0"]
                                  [ring/ring-core "1.6.3"]
                                  [ring-middleware-format "0.7.2"]
                                  [ring-transit "0.1.6"]
                                  [ring/ring-json "0.4.0"]

                                  ;; extra formatters
                                  [circleci/clj-yaml "0.5.6"]
                                  [clojure-msgpack "1.2.1" :exclusions [org.clojure/clojure]]
                                  [metosin/jsonista "0.1.0-SNAPSHOT"]

                                  ;; Pedestal
                                  [org.clojure/core.async "0.3.465"]
                                  [io.pedestal/pedestal.service "0.5.3" :exclusions [org.clojure/tools.reader
                                                                                     org.clojure/core.async
                                                                                     org.clojure/core.memoize]]
                                  [javax.servlet/javax.servlet-api "4.0.0"]
                                  [org.slf4j/slf4j-log4j12 "1.7.25"]

                                  [criterium "0.4.4"]]}
             :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :1.9 {:dependencies [[org.clojure/clojure "1.9.0-alpha15"]]}
             :perf {:jvm-opts ^:replace ["-server"
                                         "-Xmx4096m"
                                         "-Dclojure.compiler.direct-linking=true"]}
             :analyze {:jvm-opts ^:replace ["-server"
                                            "-Dclojure.compiler.direct-linking=true"
                                            "-XX:+PrintCompilation"
                                            "-XX:+UnlockDiagnosticVMOptions"
                                            "-XX:+PrintInlining"]}}
  :aliases {"all" ["with-profile" "dev:dev,1.7:dev,1.9"]
            "perf" ["with-profile" "default,dev,perf"]
            "analyze" ["with-profile" "default,dev,analyze"]})
