## har2gjs -- har to [groovy-jmeter](https://github.com/smicyk/groovy-jmeter) script converter

Converts .HAR files to Groovy JMeter (DSL) Scripts

## Usage

`easygen har2gjs.tmpl record.har`

### Sample output

``` groovy
@GrabConfig(systemClassLoader = true)
@Grab('net.simonix.scripts:groovy-jmeter')

@groovy.transform.BaseScript net.simonix.dsl.jmeter.TestScript script

start {
  plan {
    arguments {
      argument(name: 'var_host', value: "${jmt_host}")
      argument(name: 'var_user_nm', value: "${jmt_user_nm}")
      argument(name: 'var_user_pw', value: "${jmt_user_pw}")
    }

    defaults(protocol: 'http', domain: '${var_host}', port: 1080)

    group(users: jmt_users, rampUp: jmt_ramp) {
      cookies()


      transaction('TR0_passwd') {
        http('POST https://httpbin.org/basic-auth/user/passwd') {
	  headers {
	    header(name: 'Host', value: 'httpbin.org')
	    header(name: 'Connection', value: 'keep-alive')
	    header(name: 'Cache-Control', value: 'max-age=0')
	    header(name: 'Authorization', value: 'Basic dXNlcjpwYXNzd2Q=')
	    header(name: 'Upgrade-Insecure-Requests', value: '1')
	    header(name: 'User-Agent', value: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36')
	    header(name: 'Accept', value: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9')
	    header(name: 'Referer', value: 'https://www.google.com/')
	    header(name: 'Accept-Encoding', value: 'gzip, deflate, br')
	    header(name: 'Accept-Language', value: 'en-US,en;q=0.9,zh-CN;q=0.8,zh-TW;q=0.7,zh;q=0.6')
	  }

          check_response {
            status() eq 200
          }

          //extract_json expressions: '$..id', variables: 'var_bookId'
        }
      }

      transaction('TR0_300') {
        http('GET https://httpbin.org/status/300') {
	  headers {
	    header(name: 'Host', value: 'httpbin.org')
	    header(name: 'Connection', value: 'keep-alive')
	    header(name: 'Upgrade-Insecure-Requests', value: '1')
	    header(name: 'User-Agent', value: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36')
	    header(name: 'Accept', value: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9')
	    header(name: 'Referer', value: 'https://www.google.com/')
	    header(name: 'Accept-Encoding', value: 'gzip, deflate, br')
	    header(name: 'Accept-Language', value: 'en-US,en;q=0.9,zh-CN;q=0.8,zh-TW;q=0.7,zh;q=0.6')
	  }

          check_response {
            status() eq 200
          }

          //extract_json expressions: '$..id', variables: 'var_bookId'
        }
      }

      transaction('TR0_200') {
        http('GET https://httpbin.org/status/200') {
	  headers {
	    header(name: 'Host', value: 'httpbin.org')
	    header(name: 'Connection', value: 'keep-alive')
	    header(name: 'Upgrade-Insecure-Requests', value: '1')
	    header(name: 'User-Agent', value: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36')
	    header(name: 'Accept', value: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9')
	    header(name: 'Referer', value: 'https://www.google.com/')
	    header(name: 'Accept-Encoding', value: 'gzip, deflate, br')
	    header(name: 'Accept-Language', value: 'en-US,en;q=0.9,zh-CN;q=0.8,zh-TW;q=0.7,zh;q=0.6')
	  }

          check_response {
            status() eq 200
          }

          //extract_json expressions: '$..id', variables: 'var_bookId'
        }
      }

      transaction('TR0_uuid') {
        http('GET https://httpbin.org/uuid') {
	  headers {
	    header(name: 'Host', value: 'httpbin.org')
	    header(name: 'Connection', value: 'keep-alive')
	    header(name: 'accept', value: 'application/json')
	    header(name: 'User-Agent', value: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36')
	    header(name: 'Referer', value: 'https://httpbin.org/')
	    header(name: 'Accept-Encoding', value: 'gzip, deflate, br')
	    header(name: 'Accept-Language', value: 'en-US,en;q=0.9,zh-CN;q=0.8,zh-TW;q=0.7,zh;q=0.6')
	  }

          check_response {
            status() eq 200
          }

          //extract_json expressions: '$..id', variables: 'var_bookId'
        }
      }

      transaction('TR0_SFRUUEJJTiBpcyBhd2Vzb21l') {
        http('GET https://httpbin.org/base64/SFRUUEJJTiBpcyBhd2Vzb21l') {
	  headers {
	    header(name: 'Host', value: 'httpbin.org')
	    header(name: 'Connection', value: 'keep-alive')
	    header(name: 'accept', value: 'text/html')
	    header(name: 'User-Agent', value: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36')
	    header(name: 'Referer', value: 'https://httpbin.org/')
	    header(name: 'Accept-Encoding', value: 'gzip, deflate, br')
	    header(name: 'Accept-Language', value: 'en-US,en;q=0.9,zh-CN;q=0.8,zh-TW;q=0.7,zh;q=0.6')
	  }

          check_response {
            status() eq 200
          }

          //extract_json expressions: '$..id', variables: 'var_bookId'
        }
      }

      transaction('TR0_spec.json') {
        http('GET https://httpbin.org/spec.json') {
	  headers {
	    header(name: 'Host', value: 'httpbin.org')
	    header(name: 'Connection', value: 'keep-alive')
	    header(name: 'User-Agent', value: 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36')
	    header(name: 'Accept', value: '*/*')
	    header(name: 'Referer', value: 'https://httpbin.org/')
	    header(name: 'Accept-Encoding', value: 'gzip, deflate, br')
	    header(name: 'Accept-Language', value: 'en-US,en;q=0.9,zh-CN;q=0.8,zh-TW;q=0.7,zh;q=0.6')
	  }

          check_response {
            status() eq 200
          }

          //extract_json expressions: '$..id', variables: 'var_bookId'
        }
      }
    }

    summary(file: 'result.log', enabled: true)

    backend(name: 'InfluxDb Backend', enabled: true) {
      arguments {
        argument(name: 'influxdbMetricsSender', value: 'org.apache.jmeter.visualizers.backend.influxdb.HttpMetricsSender')
        argument(name: 'influxdbUrl', value: 'http://influx:8086/write?db=jmeter')
        argument(name: 'application', value: '${__groovy((&quot;${__TestPlanName}&quot;).replace(&apos;.jmx&apos;\,&apos;&apos;),)}')
        argument(name: 'measurement', value: 'jmeter')
        argument(name: 'summaryOnly', value: 'false')
        argument(name: 'samplersRegex', value: '.*')
        argument(name: 'percentiles', value: '90;95;99')
        argument(name: 'testTitle', value: "my app - users: ${jmt_users}, rampup: ${jmt_ramp}")
        argument(name: "eventTags", value: '')
      }
    }
  }
}
```

Motto, it is very easy to change or remove something (those transactions for e.g.) than to add them.  
Else, `easygen` templates are [super easy to customize](https://github.com/go-easygen/easygen#easygen---easy-to-use-universal-codetext-generator) to your own taste (check the [change logs](https://github.com/AntonioSun/har2gjs/commits/master) for the testimony).
