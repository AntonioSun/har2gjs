@GrabConfig(systemClassLoader = true)
@Grab('net.simonix.scripts:groovy-jmeter')

@groovy.transform.BaseScript net.simonix.dsl.jmeter.TestScript script

start {
  plan {
    variables {
      variable(name: 'c_app_host', value: '${__P(c_app_host, www.google.com)}', description: 'Test server host name')
      variable(name: 'c_app_error_kw', value: '${__P(c_app_error_kw,Wrong)}', description: 'keyword indicates wrong application returns')
      variable(name: 'c_lt_users', value: '${__P(c_lt_users, 10)}', description: 'loadtest users')
      variable(name: 'c_lt_ramp', value: '${__P(c_lt_ramp, 5)}', description: 'loadtest ramp up in seconds')
      variable(name: 'c_tt_range', value: '${__P(c_tt_range, 6000)}', description: 'Think Time: Maximum random number of ms to delay')
      variable(name: 'c_tt_delay', value: '${__P(c_tt_delay, 2000)}', description: 'Think Time: Ms to delay in addition to random time')
      variable(name: 'c_pt_range', value: '${__P(c_pt_range, 120000)}', description: 'Pace Time: Maximum random number of ms to delay')
      variable(name: 'c_pt_delay', value: '${__P(c_pt_delay, 60000)}', description: 'Pace Time: Ms to delay in addition to random time')
      variable(name: 'p_q', value: 'groovy jmeter')
      variable(name: 'p_oq', value: 'groovy jmeter')
      }

    defaults(protocol: 'https', domain: '${c_app_host}', port: 443)

    group(users: '${c_lt_users}', rampUp: '${c_lt_ramp}') {
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
      check_response {
        status() eq 200
      }
      check_response applyTo: 'parent', {
        text(not) contains '${c_app_error_kw}'
      }

      transaction('T0x_search') {
        http (method: 'GET', path: '/complete/search', name: 'T0r_search') {
          headers {
            header(name: 'Accept', value: '*/*')
          }
          params {
            param(name: 'q', value: '${p_q}')
            param(name: 'cp', value: '13')
            param(name: 'client', value: 'gws-wiz')
            param(name: 'xssi', value: 't')
            param(name: 'hl', value: 'en-CA')
            param(name: 'authuser', value: '0')
            param(name: 'gs_mss', value: '${p_q}')
            param(name: 'dpr', value: '1.5')
            
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      }

      transaction('T0x_search') {
        http (method: 'GET', path: '/search', name: 'T0r_search') {
          headers {
            header(name: 'Accept', value: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9')
          }
          params {
            param(name: 'q', value: '${p_q}')
            param(name: 'source', value: 'hp')
            param(name: 'uact', value: '5')
            param(name: 'oq', value: '${p_q}')
            param(name: 'sclient', value: 'gws-wiz')
            
          }

          // check_response {
          //   status() eq 200
          // }

          //extract_jmes expressions: '.book.id', variables: 'p_bookId'
        }
      }
    }

    summary(file: 'google.jtl') //, enabled: true
    view () // View Result Tree
  }
}
