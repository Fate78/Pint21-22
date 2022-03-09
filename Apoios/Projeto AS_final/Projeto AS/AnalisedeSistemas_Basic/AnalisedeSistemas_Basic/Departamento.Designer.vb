<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Departamento
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Me.ButtonAnterior = New System.Windows.Forms.Button()
        Me.ButtonProximo = New System.Windows.Forms.Button()
        Me.ButtonApagar = New System.Windows.Forms.Button()
        Me.ButtonGuardar = New System.Windows.Forms.Button()
        Me.ButtonNovo = New System.Windows.Forms.Button()
        Me.TextBox4 = New System.Windows.Forms.TextBox()
        Me.DEPARTAMENTOBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.BDbEdi2021DataSet1 = New AnalisedeSistemas_Basic.BDbEdi2021DataSet1()
        Me.TextBox3 = New System.Windows.Forms.TextBox()
        Me.TextBox2 = New System.Windows.Forms.TextBox()
        Me.TextBox1 = New System.Windows.Forms.TextBox()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.DataGridView1 = New System.Windows.Forms.DataGridView()
        Me.NDEPDataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.DEPDataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.TLFDataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.EMAILDataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.DESCRICAODataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.BDbEdi2021DataSet = New AnalisedeSistemas_Basic.BDbEdi2021DataSet()
        Me.BDbEdi2021DataSetBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.DEPARTAMENTOTableAdapter = New AnalisedeSistemas_Basic.BDbEdi2021DataSet1TableAdapters.DEPARTAMENTOTableAdapter()
        Me.ButtonFechar = New System.Windows.Forms.Button()
        CType(Me.DEPARTAMENTOBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.BDbEdi2021DataSet1, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.DataGridView1, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.BDbEdi2021DataSet, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.BDbEdi2021DataSetBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'ButtonAnterior
        '
        Me.ButtonAnterior.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonAnterior.Location = New System.Drawing.Point(420, 490)
        Me.ButtonAnterior.Name = "ButtonAnterior"
        Me.ButtonAnterior.Size = New System.Drawing.Size(105, 24)
        Me.ButtonAnterior.TabIndex = 32
        Me.ButtonAnterior.Text = "<--------------"
        Me.ButtonAnterior.UseVisualStyleBackColor = True
        '
        'ButtonProximo
        '
        Me.ButtonProximo.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonProximo.Location = New System.Drawing.Point(569, 490)
        Me.ButtonProximo.Name = "ButtonProximo"
        Me.ButtonProximo.Size = New System.Drawing.Size(105, 24)
        Me.ButtonProximo.TabIndex = 31
        Me.ButtonProximo.Text = "-------------->"
        Me.ButtonProximo.UseVisualStyleBackColor = True
        '
        'ButtonApagar
        '
        Me.ButtonApagar.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonApagar.Location = New System.Drawing.Point(420, 330)
        Me.ButtonApagar.Name = "ButtonApagar"
        Me.ButtonApagar.Size = New System.Drawing.Size(105, 24)
        Me.ButtonApagar.TabIndex = 30
        Me.ButtonApagar.Text = "Apagar"
        Me.ButtonApagar.UseVisualStyleBackColor = True
        '
        'ButtonGuardar
        '
        Me.ButtonGuardar.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonGuardar.Location = New System.Drawing.Point(569, 290)
        Me.ButtonGuardar.Name = "ButtonGuardar"
        Me.ButtonGuardar.Size = New System.Drawing.Size(105, 24)
        Me.ButtonGuardar.TabIndex = 29
        Me.ButtonGuardar.Text = "Guardar"
        Me.ButtonGuardar.UseVisualStyleBackColor = True
        '
        'ButtonNovo
        '
        Me.ButtonNovo.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonNovo.Location = New System.Drawing.Point(420, 290)
        Me.ButtonNovo.Name = "ButtonNovo"
        Me.ButtonNovo.Size = New System.Drawing.Size(105, 24)
        Me.ButtonNovo.TabIndex = 28
        Me.ButtonNovo.Text = "Novo"
        Me.ButtonNovo.UseVisualStyleBackColor = True
        '
        'TextBox4
        '
        Me.TextBox4.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.DEPARTAMENTOBindingSource, "DESCRICAO", True))
        Me.TextBox4.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox4.Location = New System.Drawing.Point(146, 410)
        Me.TextBox4.Name = "TextBox4"
        Me.TextBox4.Size = New System.Drawing.Size(213, 23)
        Me.TextBox4.TabIndex = 26
        '
        'DEPARTAMENTOBindingSource
        '
        Me.DEPARTAMENTOBindingSource.DataMember = "DEPARTAMENTO"
        Me.DEPARTAMENTOBindingSource.DataSource = Me.BDbEdi2021DataSet1
        '
        'BDbEdi2021DataSet1
        '
        Me.BDbEdi2021DataSet1.DataSetName = "BDbEdi2021DataSet1"
        Me.BDbEdi2021DataSet1.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema
        '
        'TextBox3
        '
        Me.TextBox3.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.DEPARTAMENTOBindingSource, "EMAIL", True))
        Me.TextBox3.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox3.Location = New System.Drawing.Point(146, 370)
        Me.TextBox3.Name = "TextBox3"
        Me.TextBox3.Size = New System.Drawing.Size(213, 23)
        Me.TextBox3.TabIndex = 25
        '
        'TextBox2
        '
        Me.TextBox2.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.DEPARTAMENTOBindingSource, "TLF", True))
        Me.TextBox2.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox2.Location = New System.Drawing.Point(146, 330)
        Me.TextBox2.Name = "TextBox2"
        Me.TextBox2.Size = New System.Drawing.Size(213, 23)
        Me.TextBox2.TabIndex = 24
        '
        'TextBox1
        '
        Me.TextBox1.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.DEPARTAMENTOBindingSource, "DEP", True))
        Me.TextBox1.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox1.Location = New System.Drawing.Point(146, 290)
        Me.TextBox1.Name = "TextBox1"
        Me.TextBox1.Size = New System.Drawing.Size(213, 23)
        Me.TextBox1.TabIndex = 23
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label4.Location = New System.Drawing.Point(12, 410)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(94, 24)
        Me.Label4.TabIndex = 21
        Me.Label4.Text = "Descrição"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label3.Location = New System.Drawing.Point(12, 370)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(57, 24)
        Me.Label3.TabIndex = 20
        Me.Label3.Text = "Email"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label2.Location = New System.Drawing.Point(12, 330)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(85, 24)
        Me.Label2.TabIndex = 19
        Me.Label2.Text = "Telefone"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label1.Location = New System.Drawing.Point(12, 290)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(128, 24)
        Me.Label1.TabIndex = 18
        Me.Label1.Text = "Departamento"
        '
        'DataGridView1
        '
        Me.DataGridView1.AllowUserToOrderColumns = True
        Me.DataGridView1.AutoGenerateColumns = False
        Me.DataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.DataGridView1.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.NDEPDataGridViewTextBoxColumn, Me.DEPDataGridViewTextBoxColumn, Me.TLFDataGridViewTextBoxColumn, Me.EMAILDataGridViewTextBoxColumn, Me.DESCRICAODataGridViewTextBoxColumn})
        Me.DataGridView1.DataSource = Me.DEPARTAMENTOBindingSource
        Me.DataGridView1.Location = New System.Drawing.Point(12, 12)
        Me.DataGridView1.Name = "DataGridView1"
        Me.DataGridView1.Size = New System.Drawing.Size(561, 258)
        Me.DataGridView1.TabIndex = 17
        '
        'NDEPDataGridViewTextBoxColumn
        '
        Me.NDEPDataGridViewTextBoxColumn.DataPropertyName = "NDEP"
        Me.NDEPDataGridViewTextBoxColumn.HeaderText = "NDEP"
        Me.NDEPDataGridViewTextBoxColumn.Name = "NDEPDataGridViewTextBoxColumn"
        Me.NDEPDataGridViewTextBoxColumn.ReadOnly = True
        '
        'DEPDataGridViewTextBoxColumn
        '
        Me.DEPDataGridViewTextBoxColumn.DataPropertyName = "DEP"
        Me.DEPDataGridViewTextBoxColumn.HeaderText = "DEP"
        Me.DEPDataGridViewTextBoxColumn.Name = "DEPDataGridViewTextBoxColumn"
        '
        'TLFDataGridViewTextBoxColumn
        '
        Me.TLFDataGridViewTextBoxColumn.DataPropertyName = "TLF"
        Me.TLFDataGridViewTextBoxColumn.HeaderText = "TLF"
        Me.TLFDataGridViewTextBoxColumn.Name = "TLFDataGridViewTextBoxColumn"
        '
        'EMAILDataGridViewTextBoxColumn
        '
        Me.EMAILDataGridViewTextBoxColumn.DataPropertyName = "EMAIL"
        Me.EMAILDataGridViewTextBoxColumn.HeaderText = "EMAIL"
        Me.EMAILDataGridViewTextBoxColumn.Name = "EMAILDataGridViewTextBoxColumn"
        '
        'DESCRICAODataGridViewTextBoxColumn
        '
        Me.DESCRICAODataGridViewTextBoxColumn.DataPropertyName = "DESCRICAO"
        Me.DESCRICAODataGridViewTextBoxColumn.HeaderText = "DESCRICAO"
        Me.DESCRICAODataGridViewTextBoxColumn.Name = "DESCRICAODataGridViewTextBoxColumn"
        '
        'BDbEdi2021DataSet
        '
        Me.BDbEdi2021DataSet.DataSetName = "BDbEdi2021DataSet"
        Me.BDbEdi2021DataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema
        '
        'BDbEdi2021DataSetBindingSource
        '
        Me.BDbEdi2021DataSetBindingSource.DataSource = Me.BDbEdi2021DataSet
        Me.BDbEdi2021DataSetBindingSource.Position = 0
        '
        'DEPARTAMENTOTableAdapter
        '
        Me.DEPARTAMENTOTableAdapter.ClearBeforeFill = True
        '
        'ButtonFechar
        '
        Me.ButtonFechar.Font = New System.Drawing.Font("Microsoft Sans Serif", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonFechar.Location = New System.Drawing.Point(772, 12)
        Me.ButtonFechar.Name = "ButtonFechar"
        Me.ButtonFechar.Size = New System.Drawing.Size(30, 30)
        Me.ButtonFechar.TabIndex = 33
        Me.ButtonFechar.Text = "X"
        Me.ButtonFechar.UseVisualStyleBackColor = True
        '
        'Departamento
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(814, 531)
        Me.Controls.Add(Me.ButtonFechar)
        Me.Controls.Add(Me.ButtonAnterior)
        Me.Controls.Add(Me.ButtonProximo)
        Me.Controls.Add(Me.ButtonApagar)
        Me.Controls.Add(Me.ButtonGuardar)
        Me.Controls.Add(Me.ButtonNovo)
        Me.Controls.Add(Me.TextBox4)
        Me.Controls.Add(Me.TextBox3)
        Me.Controls.Add(Me.TextBox2)
        Me.Controls.Add(Me.TextBox1)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.DataGridView1)
        Me.Name = "Departamento"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Departamento"
        CType(Me.DEPARTAMENTOBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.BDbEdi2021DataSet1, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.DataGridView1, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.BDbEdi2021DataSet, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.BDbEdi2021DataSetBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents ButtonAnterior As Button
    Friend WithEvents ButtonProximo As Button
    Friend WithEvents ButtonApagar As Button
    Friend WithEvents ButtonGuardar As Button
    Friend WithEvents ButtonNovo As Button
    Friend WithEvents TextBox4 As TextBox
    Friend WithEvents TextBox3 As TextBox
    Friend WithEvents TextBox2 As TextBox
    Friend WithEvents TextBox1 As TextBox
    Friend WithEvents Label4 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents Label2 As Label
    Friend WithEvents Label1 As Label
    Friend WithEvents DataGridView1 As DataGridView
    Friend WithEvents BDbEdi2021DataSetBindingSource As BindingSource
    Friend WithEvents BDbEdi2021DataSet As BDbEdi2021DataSet
    Friend WithEvents BDbEdi2021DataSet1 As BDbEdi2021DataSet1
    Friend WithEvents DEPARTAMENTOBindingSource As BindingSource
    Friend WithEvents DEPARTAMENTOTableAdapter As BDbEdi2021DataSet1TableAdapters.DEPARTAMENTOTableAdapter
    Friend WithEvents NDEPDataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents DEPDataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents TLFDataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents EMAILDataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents DESCRICAODataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents ButtonFechar As Button
End Class
