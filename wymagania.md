# System zarządzania zadaniami (Task Manager) - Wymagania projektu

## Model danych, Repository i JdbcTemplate

Konfiguracja encji JPA, relacje, repozytoria, migracje bazy danych oraz bezpośrednie zapytania SQL

### Encje JPA z relacjami
- Encje z `@Entity`, `@Id`, `@GeneratedValue`, `@Column`. 
- Relacje `@OneToMany`/`@ManyToOne` z `@JoinColumn`, `@ManyToMany` z `@JoinTable`. 
- **4%**

### JpaRepository i custom queries

- Repository rozszerzające `JpaRepository<T, ID>` z custom query methods (`findBy`... lub `@Query`). 
- Pageable support (`Page<T>`) dla paginacji. 
- Konfiguracja w `application.yml` (datasource, jpa, hibernate). 
- Używanie plików .sql do inicjalizacji bazy/danych
- **5%**

### JdbcTemplate - zapytania SQL

- JdbcTemplate jako dependency. 
- Zapytania SELECT z query() i RowMapper. 
- Operacje INSERT/UPDATE/DELETE z update(). 
- Użycie w serwisie lub dedykowanym DAO.
- **5%**

## REST API

### Pełna implementacja REST API z prawidłowymi kodami HTTP i dokumentacją

- CRUD Endpoints
- @RestController z @RequestMapping("/api/v1/..."). 
- GET (lista z paginacją, single), 
- POST (tworzenie), PUT (aktualizacja), 
- DELETE. 
- @PathVariable, @RequestBody, @RequestParam. 
- ResponseEntity z kodami HTTP (200, 201, 204, 400, 404).
- **7%**

### Dokumentacja API - Swagger

- Springdoc OpenAPI i Swagger UI dostępny pod /swagger-ui.html. Poprawna dokumentacja endpointów.
- **5%**

## Warstwa aplikacji - Business Logic

### Service i Transactional

- @Service, @Transactional(readOnly = true/false). 
- Dependency injection przez konstruktor. 
- Mapowanie Entity ↔ DTO. 
- Własne wyjątki (ResourceNotFoundException). 
- @RestControllerAdvice + @ExceptionHandler dla globalnej obsługi błędów.
- **3%**

### Walidacja danych

- Walidacja w DTO: @NotNull, @NotBlank, @Size, @Email, @Valid. 
- Bean Validation na poziomie kontrolera i serwisu. 
- Spójne komunikaty błędów.
- **3%**

### Thymeleaf - widoki

- @Controller z Model i @ModelAttribute. 
- Widoki z th:each (listy), th:object/th:field (formularze). 
- Wyświetlanie błędów (th:errors). 
- Layout z fragmentami (th:fragment, th:replace). 
- Bootstrap 5 styling.
- **3%**

### Operacje na plikach i eksport

- Upload plików (MultipartFile, enctype="multipart/form-data"). 
- Zapis na dysk (Files.copy). 
- Download plików (Resource, ResponseEntity<byte[]>). 
- Export do CSV/PDF.
- 3%

## Spring Security

- Konfiguracja Security
- SecurityFilterChain jako @Bean. 
- authorizeHttpRequests z requestMatchers do kontroli dostępu. 
- formLogin z konfiguracją. 
- BCryptPasswordEncoder dla szyfrowania haseł. 
- UserDetailsService z loadUserByUsername.
- 4%

## Testowanie

### Testy warstwy danych: @DataJpaTest dla testowania repository. Min. 10 testów CRUD. RowMapper i custom queries.

- **2%**

### Testy serwisów

- @Mock, @InjectMocks, Mockito: when().thenReturn(), verify(). Unit testy dla logiki biznesowej.
- **3%**

### Testy REST i integracyjne

- @WebMvcTest lub @SpringBootTest dla controllerów REST. MockMvc: perform(), andExpect(). 
- @WithMockUser dla testów Security. 
- Min. 5 scenariuszy biznesowych.
- **2%**

### Coverage i jakość

- JaCoCo - coverage 70%+. 
- Raport z pokryciem kodu. 
- Testy obejmują Happy Path i Error Cases.
- **1%**

## Wymagania specyficzne dla Task Manager

### Dwie tabele: Tasks i Categories

- Tabela Tasks: id, title, description, status (TODO/IN_PROGRESS/DONE), dueDate, categoryId, createdAt, updatedAt. 
- Tabela Categories: id, name, color. Relacja ManyToOne między Tasks i Categories przez @JoinColumn.
- **13%**

### REST API CRUD dla zadań

Endpointy: 
- GET /api/tasks (z filtrowaniem po status i categoryId), 
- GET /api/tasks/{id}, 
- POST /api/tasks (tworzenie), 
- PUT /api/tasks/{id} (zmiana statusu i edycja), 
- DELETE /api/tasks/{id}. 
- Odpowiednie kody HTTP.
- **10%**

### REST API CRUD dla kategorii

Endpointy: 
- GET /api/categories, 
- POST /api/categories, 
- PUT /api/categories/{id}, 
- DELETE /api/categories/{id}. 
- Cascading delete - usunięcie kategorii zmienia categoryId na NULL w taskach.
- **7%**

### Widoki Thymeleaf - lista zadań i formularz

- Strona główna 
    - z listą zadań (th:each), 
    - filtrami po status i kategorii. 
- Formularz dodawania/edycji zadania z dropdown kategorii. 
- Bootstrap 5 styling dla responsywności.
- **7%**

### Statystyki i raport CSV

- Endpoint GET /api/tasks/export/csv zwracający plik CSV ze wszystkimi zadaniami. 
- Dashboard z licznikami: Razem, TODO, In Progress, Done. 
- Procent wykonania zadań.
- **7%**

### Search i filtry zaawansowane

- Wyszukiwanie po tytule (LIKE query z @Query). 
- Filtry po: 
    - status, 
    - kategorii, 
    - dacie deadline (before/after). 
- Sortowanie po 
    - dacie 
    - lub statusie. 
- Paginacja po 10 zadań.
- **6%**
