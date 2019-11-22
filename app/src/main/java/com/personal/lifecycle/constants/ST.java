package com.personal.lifecycle.constants;

// StateDirector
public class ST {
    public static enum FUNC {
        RECORD(false);

        public boolean mVal;

        private FUNC(boolean val) {
            mVal = val;
        }

        public static void clearAll() {
            RECORD.mVal = false;
        }
    }
}
