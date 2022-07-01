@GrabConfig(systemClassLoader = true)
@Grab('net.simonix.scripts:groovy-jmeter')

@groovy.transform.BaseScript net.simonix.dsl.jmeter.TestScript script

start {
  plan {
    variables {
      variable(name: 'c_app_host', value: '${__P(c_app_host, a.testaddressbook.com)}', description: 'Test server host name')
      variable(name: 'c_app_error_kw', value: '${__P(c_app_error_kw,Wrong)}', description: 'keyword indicates wrong application returns')
      variable(name: 'c_lt_users', value: '${__P(c_lt_users, 10)}', description: 'loadtest users')
      variable(name: 'c_lt_ramp', value: '${__P(c_lt_ramp, 5)}', description: 'loadtest ramp up in seconds')
      variable(name: 'c_tt_range', value: '${__P(c_tt_range, 6000)}', description: 'Think Time: Maximum random number of ms to delay')
      variable(name: 'c_tt_delay', value: '${__P(c_tt_delay, 2000)}', description: 'Think Time: Ms to delay in addition to random time')
      variable(name: 'c_pt_range', value: '${__P(c_pt_range, 120000)}', description: 'Pace Time: Maximum random number of ms to delay')
      variable(name: 'c_pt_delay', value: '${__P(c_pt_delay, 60000)}', description: 'Pace Time: Ms to delay in addition to random time')
      variable(name: 'p_session_email_', value: 'user@example.com')
      variable(name: 'p_session_password_', value: 'password')
      }

    defaults(protocol: 'https', domain: '${c_app_host}', port: 443)

    group(name: 'Thread Group', users: '${c_lt_users}', rampUp: '${c_lt_ramp}') {
      headers {
        header(name: 'Host', value: '${c_app_host}')
        header(name: 'Origin', value: '${c_app_host}')
        header(name: 'Referer', value: '${c_app_host}')
        header(name: 'Connection', value: 'keep-alive')
        header(name: 'Cache-Control', value: 'max-age=0')
        header(name: 'Upgrade-Insecure-Requests', value: '1')
        header(name: 'User-Agent', value: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36')
      }
      cookies()
      cache()
      // check_response {
      //   status() eq 200
      // }
      check_response applyTo: 'parent', {
        text(not) contains '${c_app_error_kw}'
      }

      jsrsampler '--== Tx: Landing Page ==--', inline: '', enabled: false
      transaction('Tx01 Landing Page') {
        
        http (method: 'GET', path: 'http://${c_app_host}/', name: 'Tx01r ${c_app_host}') {
          headers {
            header(name: 'Accept', value: 'text/html, application/xhtml+xml')
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
      }
      transaction('Think Time') {
        uniform_timer (name: 'Think Time', delay: '${c_tt_delay}', range: '${c_tt_range}')
        jsrsampler 'Think Time: Landing Page', inline: 'return true'
      }

      jsrsampler '--== Tx: Sign in ==--', inline: '', enabled: false
      transaction('Tx02 Sign in') {
        
        http (method: 'GET', path: 'http://${c_app_host}/sign_in', name: 'Tx02r sign_in') {
          headers {
            header(name: 'Accept', value: 'text/html, application/xhtml+xml')
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
        http (method: 'POST', path: 'http://${c_app_host}/session', name: 'Tx02r session') {
          headers {
            header(name: 'Content-Type', value: 'application/x-www-form-urlencoded')
            header(name: 'Accept', value: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9')
          }
          params {
            param(name: 'utf8', value: '✓')
            param(name: 'authenticity_token', value: 'xfBuy+we5xV1rQizYTHOF9P7qdVjppOk+8Qy7bkD1IpNd3byL6bIK0Zh/PCvEBs/A3KeFp+kiSE3JBd9igkDeg==')
            param(name: 'session[email]', value: '${p_session_email_}')
            param(name: 'session[password]', value: '${p_session_password_}')
            param(name: 'commit', value: 'Sign in')
            
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
        http (method: 'GET', path: 'http://${c_app_host}/', name: 'Tx02r ${c_app_host}') {
          headers {
            header(name: 'Accept', value: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9')
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
      }
      transaction('Think Time') {
        uniform_timer (name: 'Think Time', delay: '${c_tt_delay}', range: '${c_tt_range}')
        jsrsampler 'Think Time: Sign in', inline: 'return true'
      }

      jsrsampler '--== Tx: Addresses Menu ==--', inline: '', enabled: false
      transaction('Tx03 Addresses Menu') {
        
        http (method: 'GET', path: 'http://${c_app_host}/addresses', name: 'Tx03r addresses') {
          headers {
            header(name: 'Accept', value: 'text/html, application/xhtml+xml')
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
      }
      transaction('Think Time') {
        uniform_timer (name: 'Think Time', delay: '${c_tt_delay}', range: '${c_tt_range}')
        jsrsampler 'Think Time: Addresses Menu', inline: 'return true'
      }

      jsrsampler '--== Tx: Show Entry ==--', inline: '', enabled: false
      transaction('Tx04 Show Entry') {
        
        http (method: 'GET', path: 'http://${c_app_host}/addresses/6', name: 'Tx04r 6') {
          headers {
            header(name: 'Accept', value: 'text/html, application/xhtml+xml')
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
      }
      transaction('Think Time') {
        uniform_timer (name: 'Think Time', delay: '${c_tt_delay}', range: '${c_tt_range}')
        jsrsampler 'Think Time: Show Entry', inline: 'return true'
      }

      jsrsampler '--== Tx: Edit Entry ==--', inline: '', enabled: false
      transaction('Tx05 Edit Entry') {
        
        http (method: 'GET', path: 'http://${c_app_host}/addresses/6/edit', name: 'Tx05r edit') {
          headers {
            header(name: 'Accept', value: 'text/html, application/xhtml+xml')
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
      }
      transaction('Think Time') {
        uniform_timer (name: 'Think Time', delay: '${c_tt_delay}', range: '${c_tt_range}')
        jsrsampler 'Think Time: Edit Entry', inline: 'return true'
      }

      jsrsampler '--== Tx: Update Address ==--', inline: '', enabled: false
      transaction('Tx06 Update Address') {
        
        http (method: 'POST', path: 'http://${c_app_host}/addresses/6', name: 'Tx06r 6') {
          headers {
            header(name: 'Content-Type', value: 'multipart/form-data; boundary=----WebKitFormBoundaryyDYcX8UXoiomjuZB')
            header(name: 'Accept', value: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9')
          }
          body '''\
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="utf8"

✓
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="_method"

patch
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="authenticity_token"

lZ690insTZ6yYlQsIFgLEOVBae02mLTNfZ32D1OamBM2N+PbYW1Rw33lh0xOt9gnUAeQ3AC+hwfVzsGcs6Ip/Q==
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[first_name]"

a
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[last_name]"

d
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[address1]"

asdfa
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[address2]"

asdfa
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[city]"

asdfa
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[state]"

AL
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[zip_code]"

aasdfa
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[birthday]"


------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[color]"

#000000
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[age]"


------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[website]"


------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[picture]"; filename=""
Content-Type: application/octet-stream


------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[phone]"


------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[interest_climb]"

0
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[interest_dance]"

0
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[interest_read]"

0
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="address[note]"

n
------WebKitFormBoundaryyDYcX8UXoiomjuZB
Content-Disposition: form-data; name="commit"

Update Address
------WebKitFormBoundaryyDYcX8UXoiomjuZB--

'''

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
        http (method: 'GET', path: 'http://${c_app_host}/addresses/6', name: 'Tx06r 6') {
          headers {
            header(name: 'Accept', value: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9')
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
      }
      transaction('Think Time') {
        uniform_timer (name: 'Think Time', delay: '${c_tt_delay}', range: '${c_tt_range}')
        jsrsampler 'Think Time: Update Address', inline: 'return true'
      }

      jsrsampler '--== Tx: List Addresses ==--', inline: '', enabled: false
      transaction('Tx07 List Addresses') {
        
        http (method: 'GET', path: 'http://${c_app_host}/addresses', name: 'Tx07r addresses') {
          headers {
            header(name: 'Accept', value: 'text/html, application/xhtml+xml')
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
      }
      transaction('Think Time') {
        uniform_timer (name: 'Think Time', delay: '${c_tt_delay}', range: '${c_tt_range}')
        jsrsampler 'Think Time: List Addresses', inline: 'return true'
      }

      jsrsampler '--== Tx: Sign Out ==--', inline: '', enabled: false
      transaction('Tx08 Sign Out') {
        
        http (method: 'POST', path: 'http://${c_app_host}/sign_out', name: 'Tx08r sign_out') {
          headers {
            header(name: 'Content-Type', value: 'application/x-www-form-urlencoded')
            header(name: 'Accept', value: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9')
          }
          params {
            param(name: '_method', value: 'delete')
            param(name: 'authenticity_token', value: 'q+mXPpmOn0WcZEXMgm159ZGhyx6M//ar93CIJfnjFyIllyP2d39M23FP8/wRkBZ0BZXfHO+DO6EEpT1/CEAObg==')
            
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
        http (method: 'GET', path: 'http://${c_app_host}/sign_in', name: 'Tx08r sign_in') {
          headers {
            header(name: 'Accept', value: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9')
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      
      }
      transaction('Think Time') {
        uniform_timer (name: 'Think Time', delay: '${c_tt_delay}', range: '${c_tt_range}')
        jsrsampler 'Think Time: Sign Out', inline: 'return true'
      }
      transaction('Pace Time') {
	uniform_timer (name: 'Pace Time', delay: '${c_pt_delay}', range: '${c_pt_range}')
	jsrsampler 'Pace Time', inline: 'return true'
      }
    }

    summary(file: 'testaddressbook.jtl') //, enabled: true
    view () // View Result Tree
  }
}
