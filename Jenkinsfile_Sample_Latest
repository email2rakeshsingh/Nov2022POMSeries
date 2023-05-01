pipeline{
    agent any
    
    stages{
        
        stage("build"){
            
            steps{
                echo("build the project")
            }
        }
    
    
        stage("Run your unit test"){
            
            steps{
                echo("Run the UTis")
            }
        }
    
        stage("Run integration test"){
            
            steps{
                echo("Run the ITs")
            }
        }
        
        stage("Deploy to dev"){
            
            steps{
                echo(" Deploy to dev environment")
            }
        }
    
        stage("Deploy to QA"){
            
            steps{
                echo(" Deploy to QA environment")
            }
        }
        
        stage("Run test case to QA"){
            
            steps{
                echo("Deploy to QA")
            }
        } 
        
        stage("Deploy to stage"){
            
            steps{
                echo("Deploy to stage")
            }
        }
        
        stage("Run sanity test case to QA"){
            
            steps{
                echo("Run the sanity test case in QA")
            }
        }
        stage("Deploy to PROD"){
            
            steps{
                echo("Deploy to PROD")
            }
        }
    }
}