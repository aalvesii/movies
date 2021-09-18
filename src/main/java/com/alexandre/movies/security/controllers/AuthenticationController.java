package com.alexandre.movies.security.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexandre.movies.exceptions.MovieException;
import com.alexandre.movies.security.dto.JwtAuthenticationDto;
import com.alexandre.movies.security.dto.TokenDto;
import com.alexandre.movies.security.utils.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Gera e retorna um novo token JWT.
	 * 
	 * @param authenticationDto
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws AuthenticationException
	 * @throws MovieException
	 */
	@PostMapping
	public ResponseEntity<TokenDto> gerarTokenJwt(@Valid @RequestBody JwtAuthenticationDto authenticationDto,
			BindingResult result) throws AuthenticationException, MovieException {
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();

			List<String> errorMessages = errors.stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.toList());

			String error = errorMessages.stream().map(Object::toString).collect(Collectors.joining(","));

			throw new MovieException(error);
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getSenha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
		String token = jwtTokenUtil.obterToken(userDetails);

		return ResponseEntity.ok(new TokenDto(token));
	}

	/**
	 * Gera um novo token com uma nova data de expiração.
	 * 
	 * @param request
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws MovieException
	 */
	@PostMapping(value = "/refresh")
	public ResponseEntity<TokenDto> gerarRefreshTokenJwt(HttpServletRequest request) throws MovieException {
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
		}

		if (!token.isPresent()) {
			throw new MovieException("Token não informado");
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			throw new MovieException("Token inválido ou expirado.");
		}

		String refreshedToken = jwtTokenUtil.refreshToken(token.get());

		return ResponseEntity.ok(new TokenDto(refreshedToken));
	}

}
