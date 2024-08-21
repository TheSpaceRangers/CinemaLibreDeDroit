package fr.biohabitat.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Désactiver CSRF pour simplifier le développement (à ne pas utiliser en production sans mesures supplémentaires)
                .authorizeRequests()
                .antMatchers("/signIn", "/signUp", "/**").permitAll()  // Permet l'accès non authentifié
                .anyRequest().permitAll()  // Permet l'accès à toutes les autres URL sans authentification
                .and()
                .formLogin()
                .disable()  // Désactiver la page de connexion par défaut
                .logout()
                .disable();  // Désactiver la fonctionnalité de déconnexion
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Ignorer la sécurité pour les ressources statiques
        web.ignoring().antMatchers("/resources/**", "/static/**");
    }
}
