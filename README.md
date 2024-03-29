# My Contacts

## 3 actividades
- HomeActivity
- ContactDetailActivity
- SettingsActivity

## Paso de 4 parámetros putExtra
- De HomeActivity a ContactDetailActivity

## Muestra de diálogo en respuesta a una acción del usuario
- Al añadir nuevo contacto
- Al editar un contacto
- Al borrar un contacto

## Guarda en sesión con SharedPreferences el tema de la aplicación modo Day o modo Night
- Clase SessionManager
- Clase SharedFunctions contiene la función que es usada en HomeActivity y en SettingsActivity

## Base de datos SQLite para los contactos que contiene
- DatabaseManager
- ContactDAO
- Contact

## RecyclerView que muestra contactos con 3 eventos de click con función lambda
- Muestra la lista de contactos añadidos en HomeActivity
- Evento en el que al hacer click en un contacto, se dirige a ContactDetailActivity para ver los datos de ese contacto
- Evento para editar contacto haciendo click en el botón edit
- Evento para borrar contacto haciendo click en el botón delete

## Muestra un menú en la AppBar
- En HomeActivity con el nombre de la aplicación de título y con el menu item Settings, que dirige a SettingsActivity
- En SettingsActivity con el string Settings de título y con el botón de volver a HomeActivity
- En ContactDetailActivity con el string del nombre del contacto correspondiente de título y con el botón de volver a HomeActivity

## Internacionalización
- En inglés, todos los textos empleados en el archivo strings

## ViewBinding
- Empleado en todas las activities y en ContactAdapter

## TextField de Material Design empleado en dialog_add_contact (layout también usado al editar un contacto)
- Se combina con dos etiquetas, TextInputLayout y de child TextInputEditText

## Funcionalidades adicionales
- Buscador en HomeActivity en modo onQueryTextChange
- Al no haber contactos sale mensaje de añadir tu primer contacto, que desaparece al añadir uno y solo reaparece si se borran todos los contactos
- Al añadir o editar contactos, el nombre es obligatorio y capitaliza las palabras
- Tanto en la lista de HomeActivity como en el ContactDetailActivity, Sobre un círculo aparece la letra inicial del nombre, y la del apellido si existiera
- Al añadir o editar contactos, el teléfono es obligatorio y se restringe a números y el carácter +
- Al añadir o editar contactos, el email no es obligatorio, pero comprueba que es un email válido en caso de que se añada uno