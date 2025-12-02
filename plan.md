# System zarządzania zadaniami (Task Manager) - Plan implementacji

## Konfiguracja i Autoryzacja

### 1.1 Konfiguracja projektu Spring Boot

- Inicjalizacja projektu Spring Boot z zależnościami:
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Thymeleaf
  - H2 Database (lub PostgreSQL)
  - Validation
  - Springdoc OpenAPI (Swagger)
- Konfiguracja `application.yml`:
  - Datasource
  - JPA/Hibernate settings
  - Security settings

### 1.2 Model użytkownika i autoryzacja

Form-based authentication

- **Encja User** (`@Entity`):
  - id, username, email, password, role, createdAt, updatedAt
- **UserRepository** (`JpaRepository<User, Long>`)
- **UserDTO** z walidacją (`@NotBlank`, `@Email`, `@Size`)
- **UserDetailsService** implementacja z `loadUserByUsername()`
- **BCryptPasswordEncoder** jako `@Bean`
- **SecurityConfig**:
  - `SecurityFilterChain` jako `@Bean`
  - `authorizeHttpRequests` z `requestMatchers`:
    - Publiczne: `/register`, `/login`, `/css/**`, `/js/**`
    - Chronione: wszystkie pozostałe
  - `formLogin()` z konfiguracją (successUrl, failureUrl)
  - `logout()` konfiguracja
  - **Zarządzanie sesją** (domyślnie włączone):
    - Sesja HTTP (JSESSIONID cookie) tworzona automatycznie po zalogowaniu
    - `sessionManagement()` - konfiguracja (opcjonalnie):
      - `maximumSessions()` - limit równoczesnych sesji
      - `sessionCreationPolicy` - strategia tworzenia sesji (domyślnie IF_REQUIRED)
    - Sesja przechowuje `Authentication` obiekt z danymi użytkownika
    - Weryfikacja sesji przy każdym request przez SecurityFilterChain
    - Automatyczne wylogowanie po wygaśnięciu sesji lub `logout()`

### 1.3 Widoki rejestracji i logowania (Thymeleaf)
- **AuthController** (`@Controller`):
  - `GET /register` - formularz rejestracji
  - `POST /register` - przetwarzanie rejestracji
  - `GET /login` - formularz logowania
  - `GET /` - przekierowanie do `/tasks` (po zalogowaniu)
- **Widoki Thymeleaf**:
  - `register.html` - formularz rejestracji z `th:object`, `th:field`, `th:errors`
  - `login.html` - formularz logowania
  - Layout z fragmentami (`th:fragment`, `th:replace`)
  - Bootstrap 5 styling

### 1.4 Serwis użytkownika
- **UserService** (`@Service`, `@Transactional`):
  - `registerUser(UserDTO)` - rejestracja z hashowaniem hasła
  - Dependency injection przez konstruktor
  - Mapowanie Entity ↔ DTO
  - Własne wyjątki (`UserAlreadyExistsException`)
- **GlobalExceptionHandler** (`@RestControllerAdvice`):
  - `@ExceptionHandler` dla obsługi błędów rejestracji/logowania