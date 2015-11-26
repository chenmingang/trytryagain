package main

import "github.com/go-martini/martini"
import "github.com/martini-contrib/render"
import "database/sql"
import _ "github.com/go-sql-driver/mysql"

func main() {
  db, err := sql.Open("mysql", "user:password@/dbname")
  m := martini.Classic()
  m.Use(render.Renderer(render.Options{
    Directory:  "view",
    Extensions: []string{".tmpl", ".html"},
  }))
  m.Get("/", func() string {
    return "Hello world!"
  })
  m.Get("/i", func(r render.Render) {
    r.HTML(200, "page", nil) 
 })
  m.Run()
}
