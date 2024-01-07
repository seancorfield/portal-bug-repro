;; jack-in using the :repl alias
;; eval each form in turn here
(ns repro)

(require 'portal.api)

(portal.api/open {:launcher :vs-code})

(add-tap portal.api/submit)

(= 1 1) ; true should show up in portal

(require 'clojure.test)

(clojure.test/deftest simple
  (clojure.test/is (= 1 1)))

(clojure.test/run-tests 'repro) ; test results should show up

;; the successful test report for this breaks portal:
(clojure.test/deftest object
  (let [o (reify Object
            (toString [_] "test")
            (hashCode [_] (throw (Exception. "test"))))]
    (clojure.test/is (= [o] [o]))))

(clojure.test/run-tests 'repro) ; test results do not show up

(= 1 1) ; this won't show up either

; no further evals show up in portal until you clear the portal window
; the hashCode method has to throw for this to break I think
