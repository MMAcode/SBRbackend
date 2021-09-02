# swagger
http://localhost:8080/v3/api-docs
http://localhost:8080/swagger-ui.html
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

# REST
http://localhost:8080/rest/users/search/findByFirstName?firstName=MiroBuilder
http://localhost:8080/rest/users/search/findByLastNameContains?lastName=M
http://localhost:8080/rest/users/
{
"firstName": "a",
"lastName": "a",
"email": "a"
}

# Manual Controllers
http://localhost:8080/api/users

http://localhost:8080/users

# Spring security
logout url: http://localhost:8080/logout
http://localhost:8080/login?logout


# Testing

# DB
@Sql({"/employees_schema.sql", "/import_employees.sql"})
public class SpringBootInitialLoadIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testLoadDataForTestClass() {
        assertEquals(3, employeeRepository.findAll().size());
    }
}