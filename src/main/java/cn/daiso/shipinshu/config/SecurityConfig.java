package cn.daiso.shipinshu.config;

import cn.daiso.shipinshu.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import java.io.PrintWriter;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;


    public SecurityConfig(@Lazy JwtAuthenticationFilter jwtAuthenticationFilter, CorsConfigurationSource corsConfigurationSource) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    /**
     * 密码加密器
     * @return 密码加密器的实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/home", "/csrf", "/api/login", "/api/lecture/list").permitAll()
                        .requestMatchers("/api/lectures").hasRole("STUDENT")
                        .requestMatchers("api/lectures/my").hasRole("STUDENT")
                        .requestMatchers("api/lectures/recommendedLecture").hasRole("STUDENT")
                        .requestMatchers("api/lectures/{lectureId}").hasRole("STUDENT")
                        .requestMatchers("api/lectures/{lectureId}/videos").hasRole("STUDENT")

                        .requestMatchers("/api/user-collection").hasRole("STUDENT")
                        .requestMatchers("/api/user-collection/{LectureId}").hasRole("STUDENT")
                        .requestMatchers("/api/user-collection/my").hasRole("STUDENT")

                        .requestMatchers("/api/videos/{video_id}").hasRole("STUDENT")
                        .requestMatchers("/api/videos/upload").hasRole("STUDENT")

                        .requestMatchers("/api/notes/certainNote/{note_id}").hasRole("STUDENT")
                        .requestMatchers("/api/notes/videoNotes/{video_id}").hasRole("STUDENT")
                        .requestMatchers("/api/notes/captions/{video_id}").hasRole("STUDENT")

                        .requestMatchers("/api/picture/videoCover/{video_id}").hasRole("STUDENT")

                        .requestMatchers(HttpMethod.POST, "/api/captions").hasRole("VIDEO_PROCESSOR")



                        .anyRequest().authenticated()
                )
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .httpBasic(withDefaults -> {})
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write("{\"message\":\"logout success\"}");
                            out.flush();
                        })
                        .permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource));

        return http.build();
    }
}