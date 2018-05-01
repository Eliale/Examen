CREATE TABLE [dbo].[Grades](
	[Grade] [int] NOT NULL,
	[Min_Mark] [int] NULL,
	[Max_Mark] [int] NULL,
 CONSTRAINT [PK_Grades] PRIMARY KEY CLUSTERED 
(
	[Grade] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[Students](
	[id] [int] NOT NULL,
	[Name] [varchar](50) NULL,
	[Marks] [int] NULL,
 CONSTRAINT [PK_Students] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

  -- Solucion al ejercicio en SQL
  create function getGradeFun
 (@Mark int)
  returns int
  AS
  begin
  declare @calificacion int
  set @calificacion =  (select Grade from Grades WHERE Max_Mark >= @Mark and Min_Mark <= @Mark);
   return @calificacion
  end
GO

SELECT  s.Name ,dbo.getGradeFun(s.Marks)as Calificacion, s.Marks from Students AS s
order by Calificacion desc , s.Name;
