package cine.usuario.service;

import cine.mapper.UsuarioMapper;
import cine.role.Role;
import cine.usuario.UsuarioEntity;
import cine.usuario.dto.UsuarioDtoRequest;
import cine.usuario.dto.UsuarioDtoResponse;
import cine.usuario.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UsuarioMapper usuarioMapper;


    private UsuarioDtoResponse crearUsuarioConRole (UsuarioDtoRequest request,Role role){
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()){
            throw new EntityExistsException(("el usuario ya esta registrado"));
        }
        UsuarioEntity entity=usuarioMapper.requestToEntity(request);
        String passworCodificado = passwordEncoder.encode(request.getPassword());
        entity.setPassword(passworCodificado);
        entity.setRole(role);

        UsuarioEntity guardado=usuarioRepository.save(entity);
        return usuarioMapper.entityToResponse(guardado);

    }
    @Transactional
    public UsuarioDtoResponse crearUsuario (UsuarioDtoRequest request){
        return crearUsuarioConRole(request,Role.USER);
    }
    @Transactional
    public UsuarioDtoResponse crearAdmin (UsuarioDtoRequest request){
        return crearUsuarioConRole(request,Role.ADMIN);}

    public  UsuarioDtoResponse buscarUsuarioPorMail (String email){
        UsuarioEntity entity = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->new EntityNotFoundException(
                                "El usuario " + email + " no se ha encontrado.")
                );
        return  usuarioMapper.entityToResponse(entity);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioDtoResponse> listarUsuarios(Pageable pageable) {

        return usuarioRepository.findAll(pageable)
                .map(usuarioMapper::entityToResponse);
    }

    @Transactional
    public void eliminarPorEmail(String email) {

        UsuarioEntity usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuario no encontrado")
                );
        usuarioRepository.delete(usuario);
    }



}
