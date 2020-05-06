
; Listar todas las clases: (list-defclasses)
; Listar las clases con las herencias: (browse-classes <class>)

(defclass person
	(is-a USER)
	(multislot person-name
		(type STRING)
		(cardinality  1 2)
		(default "no name")
	)
	(multislot person-lastname
		(type STRING)
		(cardinality  1 2)
		(default "no lastname")
	)
	(slot age
		(type NUMBER)
		(default 5)
	)
)

(defclass teacher
	(is-a person)
)

(defclass student
	(is-a person)
)

;----------------------------------------------------------

; Crear Instancias.
(make-instance 
	czhizhpon_student of student 
	(person-name "Cesar" "Eduardo")
	(person-lastname "Zhizhpon" "Tacuri")
	(age 21)
)

(make-instance 
	dsarmiento_student of student 
	(person-name "Douglas" "Bryan")
	(person-lastname "Sarmiento" "Basurto")
	(age 20)
)

;Eliminar instancia
(unmake-instance [<instance>])

;Guardar instancias
(save-instances "file_path")

;Cargar instancias
(load-instances "file_path")

;Imprimir 
(instances)
(list-definstances)

;----------------------------------------------------------
; send print: imprimir todos los valores de las instancias
(send [czhizhpon_student] print)

; send get: recuperar valores.
(send [czhizhpon_student] get-age)
(send [czhizhpon_student] get-person-name)
(send [czhizhpon_student] get-person-lastname)

; send put: Establecer los valores de una instancia.
(send [czhizhpon_student] put-age 22)


; Recuperar valores multislot.
(nth$ 1 (send [czhizhpon_student] get-person-name))


;----------------------------------------------------------
; Reglas

(defrule print-age
	?person <- (object 	(is-a person)
						(person-name $?n)
						(age ?a))
=>
	(printout t "Name[" (nth$ 1 $?n) "] | Age[" ?a "]" crlf)
)

;----------------------------------------------------------
; find-instance
; find-all-instances
; do-for-all-instances

(do-for-all-instances ((?p person))
	(>= ?p:age 18)
	(printout t "nombre:" ?p:person-name " | age:" ?p:age crlf)
)

(deffunction mean
	(?cl)
	(bind ?n 0)
	(bind ?sum 0)
	(do-for-all-instances ((?c ?cl)) TRUE
		(bind ?sum (+ ?sum ?c:age))
		(bind ?n (+ ?n 1))
	)
	(/ ?sum ?n)
)
