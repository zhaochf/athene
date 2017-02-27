<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-reverse-engineering
  SYSTEM "http://hibernate.sourceforge.net/hibernate-reverse-engineering-3.0.dtd" >

<hibernate-reverse-engineering>

    <type-mapping>
        <!-- jdbc-type is name fom java.sql.Types -->
        <sql-type jdbc-type="TINYINT" length='1' hibernate-type="boolean"/>
        <sql-type jdbc-type="BIGINT" hibernate-type="java.lang.Long"/>
        <sql-type jdbc-type="INTEGER" hibernate-type="java.lang.Integer"/>
        <sql-type jdbc-type="NUMERIC" hibernate-type="java.lang.Long"/>
        <sql-type jdbc-type="DATE" hibernate-type="java.time.LocalDate"/>
        <sql-type jdbc-type="TIMESTAMP" hibernate-type="java.time.ZonedDateTime"/>
    </type-mapping>

    <!-- Exclude special tables from all catalogs/schemas
    <table-filter match-name="USER" exclude="true"/> -->

</hibernate-reverse-engineering>
