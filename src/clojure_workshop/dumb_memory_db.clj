(ns clojure-workshop.dumb-memory-db)

(def memory-db (atom {}))
(defn read-db [] @memory-db)
(defn write-db [new-db] (reset! memory-db new-db))

;; {:table-1 {:data [] :indexes {}} :table-2 {:data [] :indexes {}}}

(defn create-table
  [table-name]
  (let [db (read-db)
        new-db (assoc db table-name {:indexes {} :data []})]
    (write-db new-db)))

(defn drop-table
  [table-name]
  (let [db (read-db)
        new-db (dissoc db table-name)]
    (write-db new-db)))

(defn insert
  "Insert a record in table. Id-key is a unique index"
  [table record id-key]
  (let [db (read-db)
        pos (count (get-in db [table :data]))
        inserted-record-db (update-in db [table :data] conj record)
        indexes (get-in inserted-record-db [table :indexes id-key])
        new-indexes (assoc indexes (id-key record) pos)
        inserted-index-db (assoc-in inserted-record-db [table :indexes id-key]
                                    new-indexes)]
    (if (contains? indexes (record id-key))
      (str "Record with index " (record id-key) " already exists in indexes map")
      (write-db inserted-index-db))))

(defn select-*
  [table-name]
  (let [db (read-db)]
    (get-in db [table-name])))

(defn select-*-where
  "select by field where field = field-value.
  field must be a table-name index"
  [table-name, field, field-value]
  (let [db (read-db)
        table (get-in db [table-name])
        indexes (get-in table [:indexes field])
        data (get-in table [:data])
        data-idx (get indexes field-value)
        record (get data data-idx)]
    record))

(create-table :client)
(create-table :fruit)
(drop-table :fruit)

(insert :client {:id 1 :name "Patrick" :age 32} :id)
(insert :client {:id 2 :name "Annelise" :age 25} :id)
(insert :client {:id 3 :name "Andrei" :age 28} :id)
(insert :client {:id 3 :name "Arthur" :age 28} :id)         ;; won't be inserted

(select-* :client)
(select-*-where :client :id 3)
