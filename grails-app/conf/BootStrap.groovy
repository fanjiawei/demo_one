import com.share3d.security.RequestMap
import com.share3d.security.Role
import com.share3d.security.User
import com.share3d.security.UserRole

import javax.servlet.http.HttpServletRequest

class BootStrap {

    def init = { servletContext ->
        HttpServletRequest.metaClass.getBaseUrl={
            return "$delegate.scheme://$delegate.serverName:$delegate.serverPort$delegate.contextPath"
        }
        //给Date类添加一个获得一天中最后一毫秒的方法
        Date.metaClass.getLastMs = {
            def calendar = (delegate+1).clearTime().toCalendar()
            calendar.add(Calendar.MILLISECOND,-1)
            return calendar.getTime()
        }

        RequestMap.executeUpdate('delete from RequestMap rm')
        [
                ['/attachment/**','ROLE_COMMON',null],
                ['/login/**','IS_AUTHENTICATED_ANONYMOUSLY',null],
                ['/logout/**','IS_AUTHENTICATED_ANONYMOUSLY',null],
                ['/user/register','IS_AUTHENTICATED_ANONYMOUSLY',null],
                ['/user/verifyRegistration','IS_AUTHENTICATED_ANONYMOUSLY',null],
                ['/user/create','IS_AUTHENTICATED_ANONYMOUSLY',null],
                ['/','ROLE_COMMON',null]
        ].each {
            new RequestMap(url: it[0],configAttribute: it[1],httpMethod: it[2]).save()
        }

        def commonUserRole =Role.findOrSaveByAuthority('ROLE_COMMON')

        def testUser = new User(username: 'test@qq.com',password: 'test',enabled: true)
        if(!testUser.save()){
            println(testUser.errors)
        }else {
            UserRole.create(testUser,commonUserRole,true)
        }
    }
    def destroy = {
    }
}
