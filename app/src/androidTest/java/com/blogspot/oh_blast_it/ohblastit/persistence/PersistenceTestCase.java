package com.blogspot.oh_blast_it.ohblastit.persistence;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.blogspot.oh_blast_it.ohblastit.testhelpers.OhBLASTItTestHelper;

/**
 * Created by hemal on 07/01/15.
 */
public class PersistenceTestCase extends InstrumentationTestCase {
    protected void setUp() throws Exception {
        super.setUp();
        OhBLASTItTestHelper helper = new OhBLASTItTestHelper(getInstrumentation().getTargetContext());
        helper.cleanDatabase();
    }
}
