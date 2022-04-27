package com.urise.webapp.storage;

import org.junit.Before;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
}