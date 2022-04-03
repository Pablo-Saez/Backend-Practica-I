package com.springboot.inventario.service.impl;

import com.springboot.inventario.entity.Producto;
import com.springboot.inventario.entity.Registeruser;
import com.springboot.inventario.entity.Zona;
import com.springboot.inventario.entity.ZonaProducto;
import com.springboot.inventario.exception.ResourceNotFoundException;
import com.springboot.inventario.payload.ZonaProductoDto;
import com.springboot.inventario.repository.ProductoRepository;
import com.springboot.inventario.repository.ZonaProductoRepository;
import com.springboot.inventario.repository.ZonaRepository;
import com.springboot.inventario.service.ZonaProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZonaProductoServiceImpl implements ZonaProductoService {
    private ZonaRepository zonaRepository;
    private ProductoRepository productoRepository;
    private ZonaProductoRepository zonaProductoRepository;

    public ZonaProductoServiceImpl(ZonaRepository zonaRepository, ProductoRepository productoRepository, ZonaProductoRepository zonaProductoRepository) {
        this.zonaRepository = zonaRepository;
        this.productoRepository = productoRepository;
        this.zonaProductoRepository = zonaProductoRepository;
    }

    @Override
    public ZonaProductoDto AgregarProducto(ZonaProductoDto zonaProductoDto) {
        ZonaProducto zonaProducto = new ZonaProducto();
        zonaProducto.setStock(zonaProductoDto.getStock());
        //buscar y setear el producto zonaproducto
        Producto producto=productoRepository.findById(zonaProductoDto.getProducto_id()).orElseThrow(
                ()-> new ResourceNotFoundException("Producto","id",zonaProductoDto.getProducto_id()));
        zonaProducto.setProducto(producto);

        //buscar y setear la zona en zonaproducto

        Zona zona =zonaRepository.findById(zonaProductoDto.getZona_id()).orElseThrow(
                ()-> new ResourceNotFoundException("Zona","id",zonaProductoDto.getZona_id()));
        zonaProducto.setZona(zona);

        ZonaProducto newZonaProducto = zonaProductoRepository.save(zonaProducto);
        return mapToDto(newZonaProducto);

    }

    @Override
    public ZonaProductoDto ObtenerZonaProducto(Long zonaId, Long productoId) {
      ZonaProducto zonaProducto=zonaProductoRepository.EncontrarProductoZona(zonaId,productoId);
      return mapToDto(zonaProducto);
    }

    @Override
    public List<ZonaProductoDto> obtenerProductosDeUnaZona(Long zonaId) {
        List<ZonaProducto> zonaProductos = zonaProductoRepository.ProductosPorZonaId(zonaId);
        return zonaProductos.stream().map(zonaProducto -> mapToDto(zonaProducto)).collect(Collectors.toList());
    }

    @Override
    public ZonaProductoDto obtenerZonaProductoPorIdProducto(Long productoId) {
        ZonaProducto zonaProducto = zonaProductoRepository.obtenerZonaProductoPorIdProducto(productoId);
        return mapToDto(zonaProducto);
    }

    @Override
    public ZonaProductoDto ActualizarStock(ZonaProductoDto zonaProductoDto) {
        ZonaProducto zonaProducto =zonaProductoRepository.EncontrarProductoZona(zonaProductoDto.getZona_id(),zonaProductoDto.getProducto_id());
        zonaProducto.setStock(zonaProductoDto.getStock());
        ZonaProducto zonaProducto1 = zonaProductoRepository.save(zonaProducto);
        return mapToDto(zonaProducto1);

    }

    @Override
    public void EliminarProductoDeZona(Long zonaId, Long productoId) {
        ZonaProducto zonaProducto=zonaProductoRepository.EncontrarProductoZona(zonaId,productoId);
        zonaProductoRepository.delete(zonaProducto);
    }


    private ZonaProductoDto mapToDto(ZonaProducto zonaProducto){
        ZonaProductoDto zonaProductoDto= new ZonaProductoDto();
        zonaProductoDto.setId(zonaProducto.getId());
        zonaProductoDto.setProducto_id(zonaProducto.getProducto().getId());
        zonaProductoDto.setZona_id(zonaProducto.getZona().getId());
        zonaProductoDto.setStock(zonaProducto.getStock());
        return zonaProductoDto;
    }
    private ZonaProducto mapToEntity(ZonaProductoDto zonaProductoDto){
        ZonaProducto zonaProducto = new ZonaProducto();
        zonaProducto.setStock(zonaProductoDto.getStock());
        return zonaProducto;
    }
}
