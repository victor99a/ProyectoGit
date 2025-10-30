package com.victor.proyecto.service;



import com.victor.proyecto.model.Usuario;

import java.util.List;

public interface IntUsuarioService {

    public List<Usuario> listarUsuarios();

    public Usuario buscarUsuarioPorId(Integer idUsuario);

    public void guardarUsuario(Usuario usuario);

    public void eliminarUsuario(Usuario usuario);
}
