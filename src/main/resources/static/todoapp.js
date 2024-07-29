
const API_BASE_URL = '/todos';
const todoForm = document.getElementById('todo-form');
const todoList = document.getElementById('todo-list');

// Fetch and display todos
function fetchTodos() {
    fetch(API_BASE_URL)
        .then(response => response.json())
        .then(todos => {
            todoList.innerHTML = '';
            todos.forEach(todo => {
                const li = createTodoElement(todo);
                todoList.appendChild(li);
            });
        })
        .catch(error => console.error('Error fetching todos:', error));
}			

// Create a todo element
function createTodoElement(todo) {
    const li = document.createElement('li');
    li.className = 'todo-item';
    if (todo.status === 'COMPLETED') {
        li.classList.add('completed');
    }
        const createdDate = new Date(todo.createdAt); 
    li.innerHTML = `
        <span>${todo.title}</span>
        <span>${todo.description}</span>
        <span>${todo.status}</span>
        <span>${createdDate.toLocaleString()}</span>
        <button onclick="completeTodo(${todo.id})">Complete</button>
    `;
    return li;
}

// Add a new todo
todoForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const title = document.getElementById('todo-title').value;
    const description = document.getElementById('todo-description').value;

    fetch(API_BASE_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ title, description }),
    })
        .then(response => response.json())
        .then(() => {
            fetchTodos();
            todoForm.reset();
        })
        .catch(error => console.error('Error adding todo:', error));
});

// Complete a todo
function completeTodo(id) {
    fetch(`${API_BASE_URL}/${id}/complete`, {
        method: 'PATCH',
    })
        .then(() => fetchTodos())
        .catch(error => console.error('Error completing todo:', error));
}

// Initial fetch of todos
fetchTodos();