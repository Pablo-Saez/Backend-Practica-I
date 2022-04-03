package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Producto;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.payload.ProductoDto;
import com.springboot.inventario.repository.ProductoRepository;
import com.springboot.inventario.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {
    private ProductoRepository productoRepository;
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    @Override
    public ProductoDto crearProducto(ProductoDto productoDto) {
        Producto producto = mapToEntity(productoDto);
        Producto newProducto=productoRepository.save(producto);
        return  mapToDto(newProducto);
    }

    @Override
    public ProductoDto obtenerProducto(Long id) {
        Producto producto=productoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Producto","id",id)
        );
        return mapToDto(producto);
    }
    @Override
    public List<ProductoDto> EncontrarTodosLosProductosDeUnInventario(Long inventarioId) {
        List<Producto> productos = productoRepository.EncontrarTodosLosProductosDeUnInventario(inventarioId);
        return productos.stream().map(producto -> mapToDto(producto)).collect(Collectors.toList());
    }
    @Override
    public ProductoDto ActualizarProducto(Long id, ProductoDto productoDto) {
        Producto producto=productoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Producto","id",id)
        );
        if(productoDto.getNombre()!=null)
        producto.setNombre(productoDto.getNombre());
        if(productoDto.getPeso()!=null)
        producto.setPeso(productoDto.getPeso());
        if(productoDto.getTipo()!=null)
        producto.setTipo(productoDto.getTipo());
        if(productoDto.getSKU()!=null)
        producto.setSKU(productoDto.getSKU());


        Producto productoActualizado = productoRepository.save(producto);
        return mapToDto(productoActualizado);
    }

    @Override
    public void  eliminarProducto(Long id) {
        Producto producto=productoRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Producto","id",id)
        );
        productoRepository.delete(producto);

    }


    private ProductoDto mapToDto(Producto producto){
        ProductoDto productoDto= new ProductoDto();
        productoDto.setId(producto.getId());
        productoDto.setNombre(producto.getNombre());
        productoDto.setPeso(producto.getPeso());
        productoDto.setSKU(producto.getSKU());
        productoDto.setTipo(producto.getTipo());
        return productoDto;
    }
    private Producto mapToEntity(ProductoDto productoDto){
        Producto producto= new Producto();
        producto.setNombre(productoDto.getNombre());
        producto.setPeso(productoDto.getPeso());
        producto.setSKU(productoDto.getSKU());
        producto.setTipo(productoDto.getTipo());
        return producto;
    }
}
