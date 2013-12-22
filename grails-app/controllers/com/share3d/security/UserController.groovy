package com.share3d.security

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityUtils

class UserController {
    def springSecurityService
    def create(){}
    def register(){
        User user = new User(params)
        user.enabled=false
        if (!user.save()){
            user.errors.getAllErrors().each {log.debug(message(error: it))}
            render([
                    success : false,
                    errors : user.errors.getAllErrors().collect {message(error: it)}
            ] as JSON)
            return
        }

        RegistrationCode registrationCode=new RegistrationCode(username: user.username)

        def url=createLink(base: request.getBaseUrl(),controller: 'user', action: 'verifyRegistration',params: [t:registrationCode.token])

        sendMail {
            to params.username
            from "${g.message(code: 'app.name')} <f37fanjiawei@126.com>"
            subject "完成注册"
            html  """
                Hi ${user.username},<br/>
                <br/>
                You (or someone pretending to be you) created an account with this email address.<br/>
                <br/>
                If you made the request, please click&nbsp;<a href="$url">here</a> to finish the registration.
                """
        }

        registrationCode.save()
        request.withFormat {
            form {
                render(view: 'registrationCodeSended',model: [userInstance:user])
            }
            "*"{
                render([success : true,data:[
                        id: user.id,
                        username:user.username
                ]] as JSON)
            }
        }
    }

    def verifyRegistration(){
        def conf = SpringSecurityUtils.securityConfig
        String defaultTargetUrl = conf.successHandler.defaultTargetUrl

        String token = params.t

        def registrationCode = token ? RegistrationCode.findByToken(token) : null
        if (!registrationCode) {
            flash.error = message(code: 'spring.security.ui.register.badCode')
            redirect uri: defaultTargetUrl
            return
        }

        def user
        RegistrationCode.withTransaction { status ->
            user = User.findByUsername(registrationCode.username)
            if (!user) {
                return
            }
            user.enabled = true
            user.save(flush:true)

            UserRole.create user, Role.findByAuthority('ROLE_COMMON')

            registrationCode.delete()
        }

        if (!user) {
            flash.error = message(code: 'spring.security.ui.register.badCode')
            redirect uri: defaultTargetUrl
            return
        }

        springSecurityService.reauthenticate user.username

        flash.message = message(code: 'spring.security.ui.register.complete')
        redirect uri: conf.ui.register.postRegisterUrl ?: defaultTargetUrl
    }
}
