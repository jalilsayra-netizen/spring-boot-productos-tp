package com.example.productos.controller;

import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // 1. Guardar un nuevo producto
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // 2. Ordenar el listado por un campo (ej: /api/productos/ordenados?campo=precio)
    @GetMapping("/ordenados")
    public List<Producto> obtenerOrdenados(@RequestParam(defaultValue = "nombre") String campo) {
        return productoRepository.findAll(Sort.by(campo).ascending());
    }

    // 3. Modificar un parámetro (actualizar el precio de un producto por ID)
    @PutMapping("/{id}/precio")
    public Producto modificarPrecio(@PathVariable Long id, @RequestParam Double nuevoPrecio) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        producto.setPrecio(nuevoPrecio);
        return productoRepository.save(producto);
    }

    // 4. Borrar el producto por ID
    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            return "El producto con ID " + id + " no existe.";
        }
        productoRepository.deleteById(id);
        return "Producto eliminado correctamente.";
    }
}
