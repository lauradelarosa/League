package com.delarosa.common

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

interface MockServerTest {

    var mockserver: MockWebServer

    @Before
    fun setUp(){
        mockserver = MockWebServer()
        mockserver.start()
    }

    @After
    fun tearDown() {
        mockserver.shutdown()
    }
}
