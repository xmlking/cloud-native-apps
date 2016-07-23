class ApplicationTests {

    @Test
    void homeSaysHello() {
        assertEquals("Hello World!", new GormPerson().home())
    }

}