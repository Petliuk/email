document.addEventListener('DOMContentLoaded', function() {
    const userListElement = document.getElementById('userList');
    const saveUserButton = document.getElementById('saveUserButton');
    const getUsersButton = document.getElementById('getUsersButton');
    const form = document.getElementById('userForm');
    const sendMessageButton = document.getElementById('sendMessageButton');

    sendMessageButton.addEventListener('click', function() {
            const message = document.getElementById('message').value; // Отримання тексту повідомлення з поля вводу
            sendMessageToAllUsers(message); // Виклик функції для відправлення повідомлення на сервер
        });


    function sendMessageToAllUsers(message) {
        fetch('http://localhost:8080/sendEmailToAllUsers', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(message) // Відправка тексту повідомлення у форматі JSON на сервер
        })
            .then(response => {
                if (response.ok) {
                    alert('Повідомлення відправлено усім користувачам.');
                    // Якщо відповідь успішна, ви можете виконати додаткові дії тут
                } else {
                    alert('Не вдалося відправити повідомлення.');
                    // Обробка помилки відправки повідомлення
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Сталася помилка при відправленні повідомлення.');
                // Обробка помилки
            });
    }



    // Функція для видалення користувача
    function deleteUser(id) {
        fetch(`http://localhost:8080/users/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    alert(`User with ID ${id} has been deleted.`);
                    // Оновлення списку користувачів після видалення
                    getUsers();
                } else {
                    throw new Error('Failed to delete user.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred while deleting user.');
            });
    }

    // Функція для отримання списку користувачів
    function getUsers() {
        fetch('http://localhost:8080/users', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Failed to fetch users.');
                }
            })
            .then(data => {
                let usersTable = '<table><tr><th>ID</th><th>Name</th><th>Surname</th><th>Email</th><th>Action</th></tr>';

                data.forEach(user => {
                    usersTable += `
          <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.email}</td>
            <td><button class="deleteButton" data-id="${user.id}">Delete</button></td>
          </tr>
        `;
                });

                usersTable += '</table>';
                userListElement.innerHTML = usersTable;

                // Додаємо обробник подій для кнопок "Delete"
                const deleteButtons = document.querySelectorAll('.deleteButton');
                deleteButtons.forEach(button => {
                    button.addEventListener('click', () => {
                        const userId = button.getAttribute('data-id');
                        deleteUser(userId);
                    });
                });
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred while fetching users.');
            });
    }

    // Функція для збереження нового користувача
    saveUserButton.addEventListener('click', function(event) {
        event.preventDefault(); // Зупинка дефолтної події форми

        const surname = document.getElementById('surname').value;
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;

        const user = {
            surname: surname,
            name: name,
            email: email
        };

        fetch('http://localhost:8080/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then(response => {
                if (response.ok) {
                    alert('User saved successfully!');
                    // Оновлення списку користувачів після додавання нового користувача
                    getUsers();
                    form.reset(); // Очистка форми після успішного збереження
                } else {
                    alert('Failed to save user.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred while saving user.');
            });
    });

    // Виклик функції для отримання списку користувачів
    getUsersButton.addEventListener('click', getUsers);
});

