// THROUGH .js file we can create multiple database which will startup pn docker intilization
databases = [ 'BAGUS' ]

for (var i = databases.length - 1; i >= 0; i--) {
	db = db.getSiblingDB(databases[i])

	db.createUser({
		user : "devadmin",
		pwd : "devadmin",
		roles : [ {
			role : "root",
			db : "BAGUS"
		} ]
	})
}

// https://phoenixnap.com/kb/docker-mongodb
// http://joseantoniopio.com/programming/mongodb-4-2-x-on-docker-that-works/
// https://stackoverflow.com/questions/42912755/how-to-create-a-db-for-mongodb-container-on-start-up
// https://medium.com/faun/managing-mongodb-on-docker-with-docker-compose-26bf8a0bbae3
