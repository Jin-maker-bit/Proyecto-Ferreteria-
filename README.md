# 🛠️ Ferretería JP Fusión — Sistema de Gestión 🛠️



> Aplicación de escritorio desarrollada en Java con NetBeans para la gestión integral de inventario, usuarios y tiendas de la cadena **Ferretería JP Fusión**.

---

## 👥 Autores

| Nombre | GitHub | Rol |
|--------|--------|-----|
| **Patricia** | [@patriciascuii-hub](https://github.com/patriciascuii-hub) | Desarrollo completo — vistas, lógica, BD y documentación |
| **Jose** | [@Jin-maker-bit](https://github.com/Jin-maker-bit) | Desarrollo completo — vistas, lógica, BD y documentación |

> 💡 Ambos integrantes han colaborado activamente en **todas las fases** del proyecto: análisis, diseño de interfaz, desarrollo de vistas, lógica de negocio, base de datos y documentación técnica Javadoc.

---

## 🚀 Estado del Proyecto

![Version](https://img.shields.io/badge/versión-1.0-gold)
![Estado](https://img.shields.io/badge/estado-completado-brightgreen)
![Java](https://img.shields.io/badge/Java-JDK%2024-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)

El proyecto se encuentra en su **Versión 1.0** con todas las funcionalidades implementadas y documentación técnica completa.

---

## 📋 Descripción

La aplicación permite gestionar los diferentes productos que se comercializan en la ferretería. Cuenta con dos perfiles de usuario con distintos niveles de acceso:

- 👤 **Usuario (user)** — Consulta de artículos, destacados, ofertas y gestión de su cuenta personal.
- 🛡️ **Administrador (admin)** — Gestión completa de artículos, usuarios, categorías, orígenes y tiendas.

Cada acceso queda registrado automáticamente en la base de datos con el usuario, la fecha y la IP del equipo desde el que se conecta.


---

## ✅ Funcionalidades implementadas

### 🛡️ Rol Admin
- ✅ Login con registro automático de accesos (usuario, fecha, IP)
- ✅ Panel de control con contadores en tiempo real (tiendas, productos, usuarios)
- ✅ Tabla de novedades con los 3 últimos artículos registrados
- ✅ Registrar nuevos artículos con cálculo automático de precio de venta
- ✅ Consultar, editar nombre/descripción y eliminar artículos
- ✅ Gestión de artículos destacados y en oferta
- ✅ Registrar y gestionar usuarios (tipo, estado, tienda asignada)
- ✅ Gestión de categorías y registro de nuevas
- ✅ Gestión de orígenes y registro de nuevos
- ✅ Gestión de tiendas y registro de nuevas
- ✅ Ver y actualizar datos de su cuenta


### 👤 Rol User
- ✅ Panel de bienvenida con contadores y últimos artículos
- ✅ Consulta de listado completo de artículos
- ✅ Consulta de artículos destacados
- ✅ Consulta de artículos en oferta
- ✅ Ver y actualizar nombre, apellidos y contraseña

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Uso |
|------------|--------| ------|
| **Java JDK 24** | Lenguaje principal de desarrollo |
| **NetBeans IDE** | Latest |Entorno de desarrollo |
| **Swing** | — |Interfaz gráfica (JFrame - JDialog) |
| **MySQL 8.0** | Base de datos relacional |
| **JDBC** | — |Conexión Java ↔ MySQL |
| **mysql-connector-j** | 9.6.0 | Driver JDBC oficial de MySQL |
| **Git + GitHub** | — |Control de versiones |

---

## 🗄️ Base de Datos

La base de datos se llama `ferreteria` y contiene las siguientes tablas:

| Tabla | Descripción |
|-------|-------------|
| `producto` | Artículos de la ferretería |
| `usuarios` | Usuarios del sistema |
| `categorias` | Categorías de productos (Menaje, Decoración, Herramientas, Jardín) |
| `origen` | Orígenes de los productos (Nacional, Importación) |
| `tiendas` | Tiendas de la cadena |
| `responsables_tienda` | Responsables asignados a cada tienda |
| `accesos` | Registro histórico de accesos al sistema |

---

## 📦 Estructura del Proyecto

```
ProyectoFerreteria/
 
  ├── 📁 imagenes/        → Recursos gráficos e iconos
  ├── 📁 audio /          → Recursos multimedia - singles   
  
  ├── 📦 bbdd/            → Conexión y consultas a BD
  │     ├── Conexion.java
  │     ├── ConsultasAccesos.java
  │     ├── ConsultasCategorias.java
  │     ├── ConsultasOrigen.java
  │     ├── ConsultasProducto.java
  │     ├── ConsultasResponsableTienda.java
  │     ├── ConsultasTiendas.java
  │     └── ConsultasUsuarios.java
  
  ├── 📦 modelo/           → Clases POJO del dominio
  │     ├── Acceso.java
  │     ├── Categoria.java
  │     ├── Origen.java
  │     ├── Producto.java
  │     ├── ResponsableTienda.java
  │     ├── Tienda.java
  │     └── Usuario.java
  
  ├── 📦 utilidades/       → Métodos reutilizables
  │     └── Utilidades.java
  
  ├──  📦 vistas/          → Interfaces gráficas

        ├── VentanaLogin.java

  ├── 📦 vistasAdmin/
        ├── VentanaAdmin.java
        ├── RegistrarArticulo.java
        ├── RegistrarUsuario.java
        ├── RegistrarCategoria.java
        ├── RegistrarOrigen.java
        ├── RegistrarTiendas.java
        ├── VerListadoArticulos.java
        ├── VerListadoDestacados.java
        ├── VerListadoOfertas.java
        ├── VerListadoUsuarios.java
        ├── VerListadoCategorias.java
        ├── VerListadoOrigen.java
        ├── VerListadoTiendas.java
        └── VerDatosCuenta.java

   ├── 📦 vistasUser/
              ├── VentanaUser.java
              ├── VerListadoArticulo.java
              ├── VerArticuloDestacado.java
              ├── VerArticuloOferta.java
              └── VerDatosCuenta.java
```

---

## ⚙️ Requisitos previos

Para ejecutar el proyecto necesitas:

1. **Java JDK 24** o superior instalado
2. **MySQL** en funcionamiento (puerto 3307)
3. **NetBeans IDE** (recomendado para desarrollo)
4. El driver **mysql-connector-j-9.6.0.jar** añadido a las librerías del proyecto

---

## 🔧 Configuración de la base de datos

1. Crea la base de datos `ferreteria` en tu servidor MySQL
2. Importa el script SQL incluido en la carpeta `/sql`
3. Verifica los parámetros de conexión en `Conexion.java`:

```java
String url = "jdbc:mysql://localhost:3307/ferreteria?serverTimezone=UTC&useSSL=false";
conn = DriverManager.getConnection(url, "root", "");
```

> ⚠️ Ajusta el puerto según tu configuración: **3307** para Windows, **8889** para MAMP en Mac con contraseña root.

---

## 📚 Documentación Javadoc

El proyecto cuenta con documentación completa siguiendo el estándar Javadoc con etiquetas `@author`, `@param`, `@return` y referencias cruzadas entre clases.

Para consultarla:
1. Navega a la carpeta `dist/javadoc`
2. Abre `index.html` en tu navegador


---

## 🎨 Diseño

La interfaz sigue la paleta de colores **Nexus Style**:

| Color | Hex | Uso |
|-------|-----|-----|
| Fondo oscuro | `#032026` | Fondo principal de ventanas |
| Fondo panel | `#093040` | Paneles interiores y campos |
| Azul grisáceo | `#70898C` | Botones secundarios y detalles |
| Dorado | `#BF9663` | Elementos principales y cabeceras |
| Dorado claro | `#BFAD8A` | Textos suaves y detalles |

---
## 📁 Repositorio

🔗 [github.com/Jin-maker-bit/Proyecto-Ferreteria-](https://github.com/Jin-maker-bit/Proyecto-Ferreteria-)

---

*Proyecto desarrollado para el módulo de **Entornos de Desarrollo** — Curso 2025/2026*
*Ciclo Formativo — Desarrollo de Aplicaciones Multiplataforma*

https://github.com/Jin-maker-bit 🐲
https://github.com/patriciascuii-hub 🐭


