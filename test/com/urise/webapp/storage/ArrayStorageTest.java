package com.urise.webapp.storage;

import org.junit.Before;

public class ArrayStorageTest extends AbstractArrayStorageTest{

    @Before
    public void setUp() throws Exception {
        storage = new ArrayStorage();
        super.setUp();
    }

}