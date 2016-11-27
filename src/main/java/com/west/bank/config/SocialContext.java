package com.west.bank.config;

import com.west.bank.utils.OAuthServiceConfig;
import com.west.bank.utils.OAuthServiceProvider;
import org.scribe.builder.api.FacebookApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableSocial
@PropertySource("classpath:social.properties")
public class SocialContext implements SocialConfigurer {

    @Autowired
    private DataSource dataSource;

    /**
     * Configures the connection factories for Facebook and Twitter.
     * @param cfConfig
     * @param env
     */
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
                env.getProperty("facebook.app.id"),
                env.getProperty("facebook.app.secret")
        ));
    }

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator(final Environment env) {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory(
                env.getProperty("facebook.app.id"),
                env.getProperty("facebook.app.secret")));
        return registry;
    }

    @Bean
    public OAuth2Parameters oAuth2Parameters(final Environment env){
        final OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
        oAuth2Parameters.setRedirectUri(env.getProperty("app.config.oauth.facebook.callback"));
        oAuth2Parameters.setScope(env.getProperty("app.config.oauth.facebook.scope"));
        return oAuth2Parameters;
    }

    @Bean
    public OAuthServiceProvider facebookServiceProvider(final Environment env){
        final OAuthServiceProvider facebookServiceProvider = new OAuthServiceProvider(facebookServiceConfig(env));
        return facebookServiceProvider;
    }


    @Bean
    public OAuthServiceConfig facebookServiceConfig(final Environment env){
        final OAuthServiceConfig facebookServiceConfig = new OAuthServiceConfig(
                env.getProperty("facebook.app.id"),
                env.getProperty("facebook.app.secret"),
                env.getProperty("app.config.oauth.facebook.callback"),
                FacebookApi.class);
        return facebookServiceConfig;
    }

    /**
     * The UserIdSource determines the account ID of the user. The example application
     * uses the username as the account ID.
     */
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator,
                /**
                 * The TextEncryptor object encrypts the authorization details of the connection. In
                 * our example, the authorization details are stored as plain text.
                 * DO NOT USE THIS IN PRODUCTION.
                 */
                Encryptors.noOpText()
        );
    }

    /**
     * This bean manages the connection flow between the account provider and
     * the example application.
     */
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
