#### Mirapolis
## UI-тесты на проверку работы функциональности страницы входа в систему
Страница входа расположена по адресу https://lmslite47vr.demo.mirapolis.ru/mira

**Чек лист** *(реализованный в автотестах)*
- Авторизация с корректными данными (fominaelena/1P73BP4Z)
- Авторизация с корректным логином, начинающимся и завершающимся несколькими пробелами, и с корректным паролем (  fominaelena  /1P73BP4Z)
- Авторизация с корректным логином и с корректным паролем, начинающимся и завершающимся несколькими пробелами (fominaelena/  1P73BP4Z  )
- Авторизация с некорректными данными (fomina/0P73BP4Z)
- Ввод некорректного логина и корректного пароля (fomina/1P73BP4Z)
- Ввод корректного логина и некорректного пароля (fominaelena/0P73BP4Z)
- Авторизация с пустыми полями (""/"")
- Авторизация с пустым полем "логин" (""/1P73BP4Z)
- Авторизация с пустым полем "пароль" (fominaelena/"")
- Ввод пробелов во все поля(" "/" ")
- Ввод пробела в поле логин (" "/1P73BP4Z)
- Ввод пробела в поле пароль (fominaelena/" ")
- Проверка смены регистра при заполнении логина (FOMINAELENA/1P73BP4Z)
- Проверка смены регистра при заполнении пароля (fominaelena/1p73bp4z)
- Проверка видимости пароля при нажатии на иконку глаз (1p73bp4z)
- Проверка видимости пароля при двойном нажатии на иконку глаз (1p73bp4z)
- Переход по ссылке «Забыли пароль?» на страницу «Восстановление пароля» и обратно
- Ввод корректного логина на странице «Восстановление пароля» (fominaelena)
- Ввод корректного email на странице «Восстановление пароля» (efomina@company.ru)
- Ввод некорректного логина на странице «Восстановление пароля» (fomina)
- Ввод некорректного email на странице «Восстановление пароля» (efomina@compa.ru)
