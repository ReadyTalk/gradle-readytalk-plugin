package com.readytalk.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class ReadyTalkReposPlugin implements Plugin<Project> {

  Project project

  String ivyLayout = '[organization]/[module]/[revision]/ivy-[revision](-[classifier]).xml'
  String ivyArtifactLayout = '[organization]/[module]/[revision]/[module]-[revision](-[classifier]).[ext]'

  Closure credentials =
  {
    username project.'artifactory_username'
    password project.'artifactory_password'
  }

  void apply(Project project) {
    this.project = project

    project.apply {
      plugin 'artifactory'
    }

    configureArtifactory()

    project.repositories {
      addAll project.buildscript.repositories
    }
  }

  void configureArtifactory() {
    project.artifactory {
      contextUrl = project.'artifactory_root'

      publish {
        repository {
          if(project.has('type') && project.'type' == 'plugin') {
            repoKey = 'plugins-snapshots-local'
          } else {
            repoKey = 'libs-snapshots-local'
          }
          
          username = project.'artifactory_username'
          password = project.'artifactory_password'

          ivy {
            ivyLayout = ivyLayout
            artifactLayout = ivyArtifactLayout
            mavenCompatible = true
          }
        }

        defaults {
          publishIvy = true
          publishPom = false
        }
      }

      resolve {
        repository {
          repoKey = 'repo'
          username = project.'artifactory_username'
          password = project.'artifactory_password'

          ivy {
            ivyLayout = ivyLayout
            artifactLayout = ivyArtifactLayout
            mavenCompatible = true
          }
        }
      }
    }
  }

}