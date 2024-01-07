# portal bug dealing with test reports

I think this is the minimal repro.

See `src/repro.clj`.

TL;DR: jack-in with `:repl` alias to get CIDER/nREPL/Portal with the
Portal middleware enabled. Eval forms in `src/repro.clj` including
running the test that has `reify Object` with `hashCode` method that
throws. The test passes, but the test report that Portal intercepts
seems to break rendering until you clear the Portal buffer.
