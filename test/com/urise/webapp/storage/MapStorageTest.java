package com.urise.webapp.storage;

import org.junit.Before;

public class MapStorageTest extends AbstractArrayStorageTest{

    public MapStorageTest(Storage storage) {
        super(new MapStorage());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
}
