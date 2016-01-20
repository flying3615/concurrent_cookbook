package Gpars

import static groovyx.gpars.actor.Actors.*

/**
 * Created by centling on 2015/11/13.
 */
class HelloWorld {

    static main(args){
        def decryptor = actor{
            loop{
                react { message ->
                    if(message instanceof String) reply message.reverse()
                    else stop()
                }
            }
        }

        def console = actor{
            decryptor 'lellarap si yvoorG'
            react{
                println 'Decrypted message :'+it
                decryptor false
            }
        }
        [decryptor,console]*.join()
    }

}
