# Lab 4 | TQS | UA

a) **Identify a couple of examples on the use of AssertJ expressive methods chaining**

```
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());

assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");

assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
```

b) **Identify an example in which you mock the behavior of the repository (and avoid involving a database).**

In the file EmployeeService_UnitTest.java we can find a couple of examples of the behavior of the repository being mocked:

```
Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john;
Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
```

c) **What is the difference between standard @Mock and @MockBean?**

####@Mock
We use the annotation @Mock when we want to create (or instanciate) a mock with Mockito. A mock prevents the need for a functional implementation of a class by giving pre-determined responses to a certain function call.

####@MockBean
This annotation is used inside the Spring Framework. This is used to add mock objects to the Spring application context. It's useful in integration testing where a particular bean needs to be mocked. When we use the annotation on a field, as well as being registered in the application context, the mock will also be injected into the field.

d) **What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?**

It's a key-value storage for confuguration properties. Among others, it contains the details to configure the persistence storage. It's a built-in mechanism for application configuration.
