# 🛒 Proyecto Mini E-Commerce  
**Autor:** Víctor Barrera  
**Curso:** GitHub  
**DUOC UC**

Este proyecto corresponde a un **Sistema Backend de Mini E-Commerce**, donde se implementa la gestión completa de **Usuarios, Categorías, Productos, Carrito de Compras y Creación de Pedidos con Checkout**.  
El desarrollo sigue buenas prácticas de arquitectura en capas (**Controller → Service → Repository**) utilizando **Java + Spring Boot**.

---
## Tecnologías Utilizadas

| Tecnología | Uso |
|-----------|-----|
| Java 17 | Lenguaje principal |
| Spring Boot | Framework backend |
| Spring Data JPA | Persistencia y repositorios |
| MySQL | Base de datos |
| Lombok | Reducción de boilerplate |
| Postman | Pruebas de API |
---

## 🧱 Arquitectura del Proyecto
src/
└── main/
└── java/
├── controller/ → Controladores REST
├── service/ → Lógica de negocio
├── repository/ → Acceso a datos con JPA
├── dto/ → Transferencia de datos
└── model/ → Entidades (tablas)

---
## 🗄 Modelo de Datos (Relaciones)

Usuario (id)
│ 1
│
│ 1
Carrito (id, usuarioId, total)
│ 1
│
│ *
CarritoItem (id, productoId, cantidad, subtotal)

Producto (id, categoriaId, nombre, precio, stock)
│ *
│
│ 1
Categoria (id, nombre)

Pedido (id, usuarioId, total, estado)
│ 1
│
│ *
DetallePedido (id, pedidoId, productoId, cantidad, precioUnitario)

## 🛍 Flujo Principal del Negocio

1. El usuario agrega productos al **Carrito**.
2. Cada item se almacena con **productoId** y **cantidad**.
3. El usuario ejecuta **Checkout**.
4. El servicio:
   - Valida stock.
   - Descuenta inventario.
   - Crea el **Pedido** y sus detalles.
   - Vacía el **Carrito**.
5. El pedido queda registrado con **estado = GENERADO**.

---

## 🧪 Pruebas con Postman

Se recomienda el siguiente orden:

| Módulo | Acción | Endpoint |
|-------|--------|----------|
| Usuario | Crear usuario | `POST /api/usuarios` |
| Categoría | Crear categoría | `POST /api/categorias` |
| Producto | Crear producto asociado a categoría | `POST /api/productos` |
| Carrito | Agregar producto al carrito | `POST /api/carrito/items?usuarioId=1` |
| Carrito | Ver carrito | `GET /api/carrito?usuarioId=1` |
| Pedido | Generar pedido (checkout) | `POST /api/pedidos/checkout?usuarioId=1` |

---
