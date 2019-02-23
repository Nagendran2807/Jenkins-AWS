#!/usr/bin/env groovy

/*
 * This script manages every pipeline.
 */
 // WARNING: NEVER ABORT A JENKINS JOB RUNNING THIS SCRIPT. EVER.

import hudson.model.*
import hudson.EnvVars
import java.net.URL
import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurperClassic
import groovy.transform.Field
import hudson.console.ModelHyperlinkNote
import java.text.SimpleDateFormat
import java.util.logging.Level
import jenkins.plugins.http_request.util.*
import hudson.remoting.ChannelClosedException
import hudson.slaves.DumbSlave
import java.net.URLEncoder
import java.security.MessageDigest
import java.util.Date


final GIT_URL = GIT_URL
final GIT_BRANCH = GIT_BRANCH
final gitCredentialsId = GIT_CREDENTIALS


@Field String MANIFEST_ID = '' // The Manifest ID for the run.
@Field String BUILD_TIME = '' // The build time of the run.

node {
	// Pre-Build steps
	stage('Start') {
		println "Pipeline started"
	}
	
}