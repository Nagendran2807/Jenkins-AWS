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

	stage('Pre-Build Check') {  // Verify all parameters and variables
		try {
			if(!GIT_BRANCH) { throw new IllegalArgumentException('GIT_BRANCH cannot be null') }
			if(!GIT_URL) { throw new IllegalArgumentException('GIT_BRANCH cannot be null') }
			if(!GIT_CREDENTIALS) { throw new IllegalArgumentException('GIT_BRANCH cannot be null') }
		} catch (Exception e) {
		println "Pre-Build check failed"
		}
	}

	stage('Checkout') {  //Checkout from Git and read the Input file.		
		TreeMap result = gitCheckout(GIT_URL, GIT_BRANCH, gitCredentialsId, true)
		}	
	
}



def gitCheckout(String gitUrl, String branch, String gitCredentialsId, boolean deletePreviousContent) {
	//Checkout from Git and read the Input file.
	println "----------------------------------------------------------"
	println "Starting CHECKOUT from git Repo " + gitUrl
	
	Integer gitTries = 0
	Integer gitMaxTries = 1	
	TreeMap result
	while(true) {
		try {
			if (deletePreviousContent) {
				deleteDir()
			}
			result = checkout(
				poll: false,
				scm: [
					$class: 'GitSCM',
					branches: [
						[
							name: branch
						]
					], 
					doGenerateSubmoduleConfigurations: false,
					extensions: [
						[$class: 'WipeWorkspace'],
						[$class: 'CloneOption', noTags: false, reference: '', shallow: true]
					],
					submoduleCfg: [], 
					userRemoteConfigs: [
						[
							credentialsId: gitCredentialsId, 
							url: gitUrl
						]
					]
				]
			)
			return result
			break
		} catch(Exception ex) {
			if(gitTries++ >= gitMaxTries) {
				prinltln "Failed to checkout"
			}
			sleep 60
		}
	}
	println "Completed CHECKOUT from git Repo " + gitUrl
	println "----------------------------------------------------------"
}
