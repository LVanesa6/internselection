# Gestión de Usuarios Web

Este proyecto es una aplicación web de gestión de usuarios, que permite registrar, modificar, visualizar y eliminar usuarios con autenticación segura mediante Keycloak. Está construido con **React** en el frontend y **Spring Boot** en el backend, y utiliza **PostgreSQL** como sistema de base de datos.

## Tabla de Contenidos

- [Características](#características)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Ejecución](#ejecución)
- [Capturas de Pantalla](#capturas-de-pantalla)
- [Autor](#autor)

## Características

- Registro de nuevos usuarios.
- Autenticación y autorización mediante Keycloak.
- Gestión de roles y estados (activo/inactivo).
- CRUD completo de usuarios desde el frontend.
- Diseño responsivo y moderno.
- Comunicación entre frontend y backend a través de API REST.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalado lo siguiente:

- Node.js >= 16.x
- Java 17 o superior
- Maven
- PostgreSQL
- Keycloak (configurado con clientes y roles adecuados)

## Instalación

### 1. Clona el repositorio

```bash
git clone https://github.com/tuusuario/gestion-usuarios-web.git
cd gestion-usuarios-web
