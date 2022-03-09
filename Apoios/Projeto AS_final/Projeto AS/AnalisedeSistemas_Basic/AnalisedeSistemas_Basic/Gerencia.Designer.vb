<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Gerencia
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
        Me.ButtonFechar = New System.Windows.Forms.Button()
        Me.ButtonProximo = New System.Windows.Forms.Button()
        Me.ButtonApagar = New System.Windows.Forms.Button()
        Me.ButtonGuardar = New System.Windows.Forms.Button()
        Me.ButtonNovo = New System.Windows.Forms.Button()
        Me.TextBox2 = New System.Windows.Forms.TextBox()
        Me.GERENCIABindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.BDbEdi2021DataSet2 = New AnalisedeSistemas_Basic.BDbEdi2021DataSet2()
        Me.TextBox1 = New System.Windows.Forms.TextBox()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.DataGridView1 = New System.Windows.Forms.DataGridView()
        Me.NGERENCIADataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.NGESTORDataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.NDEPDataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.GERENCIATableAdapter = New AnalisedeSistemas_Basic.BDbEdi2021DataSet2TableAdapters.GERENCIATableAdapter()
        Me.ButtonAnterior = New System.Windows.Forms.Button()
        CType(Me.GERENCIABindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.BDbEdi2021DataSet2, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.DataGridView1, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'ButtonFechar
        '
        Me.ButtonFechar.Font = New System.Drawing.Font("Microsoft Sans Serif", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonFechar.Location = New System.Drawing.Point(772, 15)
        Me.ButtonFechar.Name = "ButtonFechar"
        Me.ButtonFechar.Size = New System.Drawing.Size(30, 30)
        Me.ButtonFechar.TabIndex = 34
        Me.ButtonFechar.Text = "X"
        Me.ButtonFechar.UseVisualStyleBackColor = True
        '
        'ButtonProximo
        '
        Me.ButtonProximo.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonProximo.Location = New System.Drawing.Point(569, 492)
        Me.ButtonProximo.Name = "ButtonProximo"
        Me.ButtonProximo.Size = New System.Drawing.Size(105, 24)
        Me.ButtonProximo.TabIndex = 32
        Me.ButtonProximo.Text = "-------------->"
        Me.ButtonProximo.UseVisualStyleBackColor = True
        '
        'ButtonApagar
        '
        Me.ButtonApagar.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonApagar.Location = New System.Drawing.Point(420, 333)
        Me.ButtonApagar.Name = "ButtonApagar"
        Me.ButtonApagar.Size = New System.Drawing.Size(105, 24)
        Me.ButtonApagar.TabIndex = 31
        Me.ButtonApagar.Text = "Apagar"
        Me.ButtonApagar.UseVisualStyleBackColor = True
        '
        'ButtonGuardar
        '
        Me.ButtonGuardar.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonGuardar.Location = New System.Drawing.Point(569, 293)
        Me.ButtonGuardar.Name = "ButtonGuardar"
        Me.ButtonGuardar.Size = New System.Drawing.Size(105, 24)
        Me.ButtonGuardar.TabIndex = 30
        Me.ButtonGuardar.Text = "Guardar"
        Me.ButtonGuardar.UseVisualStyleBackColor = True
        '
        'ButtonNovo
        '
        Me.ButtonNovo.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonNovo.Location = New System.Drawing.Point(420, 293)
        Me.ButtonNovo.Name = "ButtonNovo"
        Me.ButtonNovo.Size = New System.Drawing.Size(105, 24)
        Me.ButtonNovo.TabIndex = 29
        Me.ButtonNovo.Text = "Novo"
        Me.ButtonNovo.UseVisualStyleBackColor = True
        '
        'TextBox2
        '
        Me.TextBox2.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.GERENCIABindingSource, "NDEP", True))
        Me.TextBox2.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox2.Location = New System.Drawing.Point(160, 333)
        Me.TextBox2.Name = "TextBox2"
        Me.TextBox2.Size = New System.Drawing.Size(199, 23)
        Me.TextBox2.TabIndex = 25
        '
        'GERENCIABindingSource
        '
        Me.GERENCIABindingSource.DataMember = "GERENCIA"
        Me.GERENCIABindingSource.DataSource = Me.BDbEdi2021DataSet2
        '
        'BDbEdi2021DataSet2
        '
        Me.BDbEdi2021DataSet2.DataSetName = "BDbEdi2021DataSet2"
        Me.BDbEdi2021DataSet2.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema
        '
        'TextBox1
        '
        Me.TextBox1.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.GERENCIABindingSource, "NGESTOR", True))
        Me.TextBox1.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox1.Location = New System.Drawing.Point(160, 293)
        Me.TextBox1.Name = "TextBox1"
        Me.TextBox1.Size = New System.Drawing.Size(199, 23)
        Me.TextBox1.TabIndex = 24
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label2.Location = New System.Drawing.Point(12, 333)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(142, 24)
        Me.Label2.TabIndex = 20
        Me.Label2.Text = "NDepartamento"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label1.Location = New System.Drawing.Point(12, 293)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(76, 24)
        Me.Label1.TabIndex = 19
        Me.Label1.Text = "Ngestor"
        '
        'DataGridView1
        '
        Me.DataGridView1.AllowUserToOrderColumns = True
        Me.DataGridView1.AutoGenerateColumns = False
        Me.DataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.DataGridView1.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.NGERENCIADataGridViewTextBoxColumn, Me.NGESTORDataGridViewTextBoxColumn, Me.NDEPDataGridViewTextBoxColumn})
        Me.DataGridView1.DataSource = Me.GERENCIABindingSource
        Me.DataGridView1.Location = New System.Drawing.Point(12, 15)
        Me.DataGridView1.Name = "DataGridView1"
        Me.DataGridView1.Size = New System.Drawing.Size(365, 258)
        Me.DataGridView1.TabIndex = 18
        '
        'NGERENCIADataGridViewTextBoxColumn
        '
        Me.NGERENCIADataGridViewTextBoxColumn.DataPropertyName = "NGERENCIA"
        Me.NGERENCIADataGridViewTextBoxColumn.HeaderText = "NGERENCIA"
        Me.NGERENCIADataGridViewTextBoxColumn.Name = "NGERENCIADataGridViewTextBoxColumn"
        Me.NGERENCIADataGridViewTextBoxColumn.ReadOnly = True
        '
        'NGESTORDataGridViewTextBoxColumn
        '
        Me.NGESTORDataGridViewTextBoxColumn.DataPropertyName = "NGESTOR"
        Me.NGESTORDataGridViewTextBoxColumn.HeaderText = "NGESTOR"
        Me.NGESTORDataGridViewTextBoxColumn.Name = "NGESTORDataGridViewTextBoxColumn"
        '
        'NDEPDataGridViewTextBoxColumn
        '
        Me.NDEPDataGridViewTextBoxColumn.DataPropertyName = "NDEP"
        Me.NDEPDataGridViewTextBoxColumn.HeaderText = "NDEP"
        Me.NDEPDataGridViewTextBoxColumn.Name = "NDEPDataGridViewTextBoxColumn"
        '
        'GERENCIATableAdapter
        '
        Me.GERENCIATableAdapter.ClearBeforeFill = True
        '
        'ButtonAnterior
        '
        Me.ButtonAnterior.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonAnterior.Location = New System.Drawing.Point(420, 492)
        Me.ButtonAnterior.Name = "ButtonAnterior"
        Me.ButtonAnterior.Size = New System.Drawing.Size(105, 24)
        Me.ButtonAnterior.TabIndex = 35
        Me.ButtonAnterior.Text = "<--------------"
        Me.ButtonAnterior.UseVisualStyleBackColor = True
        '
        'Gerencia
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(814, 531)
        Me.Controls.Add(Me.ButtonAnterior)
        Me.Controls.Add(Me.ButtonFechar)
        Me.Controls.Add(Me.ButtonProximo)
        Me.Controls.Add(Me.ButtonApagar)
        Me.Controls.Add(Me.ButtonGuardar)
        Me.Controls.Add(Me.ButtonNovo)
        Me.Controls.Add(Me.TextBox2)
        Me.Controls.Add(Me.TextBox1)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.DataGridView1)
        Me.Name = "Gerencia"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Gerencia"
        CType(Me.GERENCIABindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.BDbEdi2021DataSet2, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.DataGridView1, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents ButtonFechar As Button
    Friend WithEvents ButtonProximo As Button
    Friend WithEvents ButtonApagar As Button
    Friend WithEvents ButtonGuardar As Button
    Friend WithEvents ButtonNovo As Button
    Friend WithEvents TextBox2 As TextBox
    Friend WithEvents TextBox1 As TextBox
    Friend WithEvents Label2 As Label
    Friend WithEvents Label1 As Label
    Friend WithEvents DataGridView1 As DataGridView
    Friend WithEvents BDbEdi2021DataSet2 As BDbEdi2021DataSet2
    Friend WithEvents GERENCIABindingSource As BindingSource
    Friend WithEvents GERENCIATableAdapter As BDbEdi2021DataSet2TableAdapters.GERENCIATableAdapter
    Friend WithEvents NGERENCIADataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents NGESTORDataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents NDEPDataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents ButtonAnterior As Button
End Class
