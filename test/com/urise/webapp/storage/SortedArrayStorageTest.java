package com.urise.webapp.storage;

import org.junit.Before;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{
    @Before
    public void setUp() throws Exception {
        storage = new SortedArrayStorage();
        super.setUp();
    }
}